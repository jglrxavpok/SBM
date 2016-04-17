package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.modes.InvocationsExecutionMode;
import org.jglr.sbm.modes.OutputVerticesExecutionMode;
import org.jglr.sbm.modes.SizeExecutionMode;
import org.jglr.sbm.modes.VecTypeHintExecutionMode;
import org.jglr.sbm.types.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Map;

public class SBMReader implements SBMVisitor, Opcodes {

    public static final long SPIRV_MAGIC_NUMBER = 0x07230203;
    private final int[] input;
    private final ByteOrder endianness;
    private int position;

    public SBMReader(byte[] input) throws IOException {
        this.input = toUnsignedBytes(input);
        endianness = findEndianness();
    }

    private int[] toUnsignedBytes(byte[] input) {
        int[] result = new int[input.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = input[i] & 0xFF;
        }
        return result;
    }

    private ByteOrder findEndianness() throws IOException {
        long bigEndianWord = readWord(ByteOrder.BIG_ENDIAN);
        if(bigEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.BIG_ENDIAN;
        }
        position = 0;
        long littleEndianWord = readWord(ByteOrder.LITTLE_ENDIAN);
        if(littleEndianWord == SPIRV_MAGIC_NUMBER) {
            return ByteOrder.LITTLE_ENDIAN;
        }
        throw new IOException("Magic number (0x"+Long.toHexString(SPIRV_MAGIC_NUMBER)+") not found, found: 0x"+Long.toHexString(bigEndianWord)+" and 0x"+Long.toHexString(littleEndianWord));
    }

    public long nextWord() throws IOException {
        return readWord(endianness);
    }

    protected long readWord(ByteOrder endianness) throws IOException {
        long result = readWordFromArray(endianness) & 0x00000000FFFFFFFFL;
        position += 4;
        return result;
    }

    private long readWordFromArray(ByteOrder endianness) {
        if(endianness == ByteOrder.BIG_ENDIAN) {
            return ((input[3 + position] << 24) | (input[2 + position] << 16) | (input[1 + position] << 8) | (input[position]));
        } else {
            return ((input[position] << 24) | (input[1+position] << 16) | (input[2+position] << 8) | (input[3+position]));
        }
    }

    public ByteOrder getEndianness() {
        return endianness;
    }

    public SBMHeaderVisitor visitHeader() throws IOException {
        position = 4; // skip the magic number (4 bytes)
        SBMHeaderVisitor visitor = newHeaderVisitor();
        visitor.visitSpirVersion(nextWord());
        visitor.visitGeneratorMagicNumber(nextWord());
        visitor.visitBound(nextWord());
        visitor.visitInstructionSchema(nextWord());
        return visitor;
    }

    private SBMHeaderVisitor newHeaderVisitor() {
        return new HeaderCollector();
    }

    public SBMCodeVisitor visitCode() throws IOException {
        position = 5*4; // skip the header (20 bytes long)
        SBMCodeVisitor visitor = newCodeVisitor();
        while (position < input.length) {
            long opcodeFull = nextWord();
            int wordCount = (int) (opcodeFull >> 16);
            int opcodeID = (int) (opcodeFull & 0xFFFF);
            switch (opcodeID) {
                case Nop:
                    break;

                case Undef: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitUndef(resultType, resultID);
                }
                break;

                case TypeVoid: {
                    long resultID = nextWord();
                    visitor.visitVoidType(resultID);
                }
                break;

                case TypeBool: {
                    long resultID = nextWord();
                    visitor.visitBoolType(resultID);
                }
                break;

                case TypeInt: {
                    long resultID = nextWord();
                    long width = nextWord();
                    boolean isSigned = toBoolean(nextWord());
                    visitor.visitIntType(resultID, width, isSigned);
                }
                break;

                case TypeFloat: {
                    long resultID = nextWord();
                    long width = nextWord();
                    visitor.visitFloatType(resultID, width);
                }
                break;

                case TypeVec: {
                    long resultID = nextWord();
                    long componentType = nextWord();
                    long componentCount = nextWord();
                    visitor.visitVectorType(resultID, componentType, componentCount);
                }
                break;

                case TypeMatrix: {
                    long resultID = nextWord();
                    long columnType = nextWord();
                    long columnCount = nextWord();
                    visitor.visitMatrixType(resultID, columnType, columnCount);
                }
                break;

                case TypeImage: {
                    long resultID = nextWord();
                    long sampledType = nextWord();
                    Dimensionality dimensionality = nextEnumValue(Dimensionality.values());
                    ImageDepth depth = nextEnumValue(ImageDepth.values());
                    boolean arrayed = toBoolean(nextWord());
                    boolean multisampled = toBoolean(nextWord());
                    Sampling sampling = nextEnumValue(Sampling.values());
                    ImageFormat format = nextEnumValue(ImageFormat.values());
                    AccessQualifier qualifier = null;
                    if(wordCount > 9)
                        qualifier = nextEnumValue(AccessQualifier.values());
                    visitor.visitImageType(resultID, sampledType, dimensionality, depth, arrayed, multisampled, sampling, format, qualifier);
                }
                break;

                case TypeSampler: {
                    long resultID = nextWord();
                    visitor.visitSamplerType(resultID);
                }
                break;

                case TypeSampledImage: {
                    long resultID = nextWord();
                    long imageType = nextWord();
                    visitor.visitSampledImageType(resultID, imageType);
                }
                break;

                case TypeArray: {
                    long resultID = nextWord();
                    long elementType = nextWord();
                    long length = nextWord();
                    visitor.visitArrayType(resultID, elementType, length);
                }
                break;

                case TypeRuntimeArray: {
                    long resultID = nextWord();
                    long elementType = nextWord();
                    visitor.visitRuntimeArrayType(resultID, elementType);
                }
                break;

                case TypeStruct: {
                    long resultID = nextWord();
                    long[] structMembers = new long[wordCount - 2];
                    for (int i = 0; i < wordCount - 2; i++) {
                        structMembers[i] = nextWord();
                    }
                    visitor.visitStructType(resultID, structMembers);
                }
                break;

                case TypeOpaque: {
                    long resultID = nextWord();
                    String name = nextString(wordCount - 2);
                    visitor.visitOpaqueType(resultID, name);
                }
                break;

                case TypePointer: {
                    long resultID = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    long type = nextWord();
                    visitor.visitPointerType(resultID, storageClass, type);
                }
                break;

                case TypeFunction: {
                    long resultID = nextWord();
                    long returnType = nextWord();
                    int parameterCount = wordCount - 3;
                    long[] parameters = new long[parameterCount];
                    for (int i = 0; i < parameterCount; i++) {
                        parameters[i] = nextWord();
                    }
                    visitor.visitFunctionType(resultID, returnType, parameters);
                }
                break;

                case TypeEvent: {
                    long resultID = nextWord();
                    visitor.visitEventType(resultID);
                }
                break;

                case TypeDeviceEvent: {
                    long resultID = nextWord();
                    visitor.visitDeviceEventType(resultID);
                }
                break;

                case TypeReservedID: {
                    long resultID = nextWord();
                    visitor.visitReserveIDType(resultID);
                }
                break;

                case TypeQueue: {
                    long resultID = nextWord();
                    visitor.visitQueueType(resultID);
                }
                break;

                case TypePipe: {
                    long resultID = nextWord();
                    AccessQualifier qualifier = nextEnumValue(AccessQualifier.values());
                    visitor.visitPipeType(resultID, qualifier);
                }
                break;

                case TypeForwardPointer: {
                    long pointerType = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    visitor.visitForwardType(pointerType, storageClass);
                }
                break;

                case ConstantFalse:
                case ConstantTrue: {
                    long type = nextWord();
                    long resultID = nextWord();
                    if (opcodeID == ConstantFalse)
                        visitor.visitFalseConstant(type, resultID);
                    else
                        visitor.visitTrueConstant(type, resultID);
                }
                break;

                case Source: {
                    SourceLanguage language = nextEnumValue(SourceLanguage.values());
                    long version = nextWord();
                    long filenameStringID = -1;
                    String sourceCode = null;
                    if (wordCount > 3) {
                        filenameStringID = nextWord();
                        if (wordCount > 4) {
                            sourceCode = nextString(wordCount - 4);
                        }
                    }
                    visitor.visitSource(language, version, filenameStringID, sourceCode);
                }
                break;

                case SourceContinued: {
                    String source = nextString();
                    visitor.visitSourceContinued(source);
                }
                break;

                case SourceExtension: {
                    String source = nextString(wordCount - 1);
                    visitor.visitSourceExtension(source);
                }
                break;

                case Name: {
                    long target = nextWord();
                    int stringSize = wordCount - 2;
                    String name = nextString(stringSize);
                    visitor.visitName(target, name);
                }
                break;

                case MemberName: {
                    long type = nextWord();
                    long target = nextWord();
                    int stringSize = wordCount - 3;
                    String name = nextString(stringSize);
                    visitor.visitMemberName(type, target, name);
                }
                break;

                case String: {
                    long resultID = nextWord();
                    String value = nextString(wordCount - 2);
                    visitor.visitString(resultID, value);
                }
                break;

                case Line: {
                    long filenameID = nextWord();
                    long line = nextWord();
                    long column = nextWord();
                    visitor.visitLine(filenameID, line, column);
                }
                break;

                case Decorate: {
                    long target = nextWord();
                    Decoration decoration = nextEnumValue(Decoration.values());
                    visitDecoration(visitor, decoration, target, wordCount);
                }
                break;

                case MemberDecorate: {
                    long structureType = nextWord();
                    long member = nextWord();
                    Decoration decoration = nextEnumValue(Decoration.values());
                    visitMemberDecoration(visitor, decoration, structureType, member, wordCount);
                }
                break;

                case EntryPoint: {
                    int savedPosition = position;
                    ExecutionModel model = nextEnumValue(ExecutionModel.values());
                    long entryPoint = nextWord();
                    String name = nextString();
                    int strSize = (position - savedPosition) / 4;
                    int interfaceCount = wordCount - strSize - 1;
                    long[] interfaces = new long[interfaceCount];
                    for (int i = 0; i < interfaceCount; i++) {
                        interfaces[i] = nextWord();
                    }
                    visitor.visitEntryPoint(model, entryPoint, name, interfaces);
                }
                break;

                case Extension: {
                    int strSize = wordCount - 1;
                    String extension = nextString(strSize);
                    visitor.visitExtension(extension);
                }
                break;

                case Capability: {
                    Capability cap = nextEnumValue(Capability.values());
                    visitor.visitCapability(cap);
                }
                break;

                case ExtInstImport: {
                    long resultID = nextWord();
                    String name = nextString(wordCount - 2);
                    visitor.visitExtendedInstructionSetImport(resultID, name);
                }
                break;

                case ExtInst: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long set = nextWord();
                    long instruction = nextWord();
                    int operandCount = wordCount - 5;
                    long[] operands = new long[operandCount];
                    for (int i = 0; i < operandCount; i++) {
                        operands[i] = nextWord();
                    }
                    visitor.visitExecExtendedInstruction(resultType, resultID, set, instruction, operands);
                }
                break;

                case MemoryModel: {
                    AddressingModel addressingModel = nextEnumValue(AddressingModel.values());
                    MemoryModel memoryModel = nextEnumValue(MemoryModel.values());
                    visitor.visitMemoryModel(addressingModel, memoryModel);
                }
                break;

                case Variable: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    long initializer = -1;
                    if (wordCount > 4)
                        initializer = nextWord();
                    visitor.visitVariable(resultType, resultID, storageClass, initializer);
                }
                break;

                case Constant: {
                    long type = nextWord();
                    long resultID = nextWord();
                    long[] bitPattern = new long[wordCount - 3];
                    for (int i = 0; i < bitPattern.length; i++) {
                        bitPattern[i] = nextWord();
                    }
                    visitor.visitConstant(type, resultID, bitPattern);
                }
                break;

                case Function: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    FunctionControl control = new FunctionControl(nextWord());
                    long funcType = nextWord();
                    visitor.visitFunction(resultType, resultID, control, funcType);
                }
                break;

                case FunctionEnd: {
                    visitor.visitFunctionEnd();
                }
                break;

                case AccessChain: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long base = nextWord();
                    long[] indexes = new long[wordCount-4];
                    for (int i = 0; i < indexes.length; i++) {
                        indexes[i] = nextWord();
                    }
                    visitor.visitAccessChain(resultType, resultID, base, indexes);
                }
                break;

                case Store: {
                    long pointer = nextWord();
                    long object = nextWord();
                    MemoryAccess memoryAccess;
                    if(wordCount > 3) {
                        memoryAccess = new MemoryAccess(nextWord());
                    } else {
                        memoryAccess = new MemoryAccess(0);
                    }
                    visitor.visitStore(pointer, object, memoryAccess);
                }
                break;

                case Label: {
                    long resultID = nextWord();
                    visitor.visitLabel(resultID);
                }
                break;

                case Return: {
                    visitor.visitReturn();
                }
                break;

                case Load: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long pointer = nextWord();
                    MemoryAccess memoryAccess;
                    if(wordCount > 4) {
                        memoryAccess = new MemoryAccess(nextWord());
                    } else {
                        memoryAccess = new MemoryAccess(0);
                    }
                    visitor.visitLoad(resultType, resultID, pointer, memoryAccess);
                }
                break;

                case ExecutionMode: {
                    long entryPoint = nextWord();
                    ExecutionMode.Type type = nextEnumValue(ExecutionMode.Type.values());
                    ExecutionMode mode = readMode(type, wordCount-3);
                    visitor.visitExecutionMode(entryPoint, mode);
                }
                break;

                default:
                    System.err.println("Unhandled: " + Opcodes.getName(opcodeID) + " " + opcodeID + " / " + wordCount);
                    position += (wordCount-1)*4;
                    break;
            }
        }
        visitor.visitEnd();
        return visitor;
    }

    private ExecutionMode readMode(ExecutionMode.Type type, int opCount) throws IOException {
        switch (type) {
            case Invocations:
                return new InvocationsExecutionMode(nextWord());

            case LocalSize:
            case LocalSizeHint:
                return new SizeExecutionMode(type, nextWord(), nextWord(), nextWord());

            case OutputVertices:
                return new OutputVerticesExecutionMode(nextWord());

            case VecTypeHint: {
                long componentCount = nextWord();
                VecTypeHintExecutionMode.DataType dataType = nextEnumValue(VecTypeHintExecutionMode.DataType.values());
                return new VecTypeHintExecutionMode(componentCount, dataType);
            }

        }
        return new ExecutionMode(type) {
            @Override
            public int getOperandCount() {
                return opCount;
            }
        };
    }

    private String nextString() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (;;) {
            long word = nextWord();

            // characters are encoded in little endian
            byte b3 = (byte) (word >> 24 & 0xFF);
            byte b2 = (byte) (word >> 16 & 0xFF);
            byte b1 = (byte) (word >> 8 & 0xFF);
            byte b0 = (byte) (word & 0xFF);

            if(b3 == 0) {
                break;
            }
            if(b2 == 0) {
                break;
            }
            if(b1 == 0) {
                break;
            }
            if(b0 == 0) {
                break;
            }
            baos.write(new byte[] {b0,b1,b2,b3});

        }
        baos.flush();
        baos.close();
        byte[] bytes = baos.toByteArray();
        // -1 to remove the null character
        return new String(bytes, "UTF-8").replace("\0", "");
    }

    private void visitDecoration(SBMCodeVisitor visitor, Decoration decoration, long target, int wordCount) throws IOException {
        switch (decoration) {
            case SpecId:
            case ArrayStride:
            case MatrixStride:
            case BuiltIn:
            case Stream:
            case Location:
            case Component:
            case Index:
            case Binding:
            case DescriptorSet:
            case Offset:
            case XfbBuffer:
            case XfbStride:
            case InputAttachmentIndex:
            case Alignment:
                visitor.visitIntDecoration(decoration, target, nextWord());
                break;

            case FuncParamAttr:
                visitor.visitFunctionParameterAttributeDecoration(target, nextEnumValue(FunctionParameterAttribute.values()));
                break;

            case FPRoundingMode:
                visitor.visitFPRoundingModeDecoration(target, nextEnumValue(FPRoundingMode.values()));
                break;

            case FPFastMathMode:
                visitor.visitFPFastMathModeDecoration(target, new FPFastMathMode(nextWord()));
                break;

            case LinkageAttributes:
                int strSize = wordCount-2;
                visitor.visitLinkageAttributesDecoration(target, nextString(strSize), nextEnumValue(LinkageType.values()));
                break;

            default:
                visitor.visitDecoration(target, decoration);
                break;
        }
    }

    private void visitMemberDecoration(SBMCodeVisitor visitor, Decoration decoration, long structureType, long member, int wordCount) throws IOException {
        switch (decoration) {
            case SpecId:
            case ArrayStride:
            case MatrixStride:
            case BuiltIn:
            case Stream:
            case Location:
            case Component:
            case Index:
            case Binding:
            case DescriptorSet:
            case Offset:
            case XfbBuffer:
            case XfbStride:
            case InputAttachmentIndex:
            case Alignment:
                visitor.visitIntMemberDecoration(decoration, structureType, member, nextWord());
                break;

            case FuncParamAttr:
                visitor.visitFunctionParameterAttributeMemberDecoration(structureType, member, nextEnumValue(FunctionParameterAttribute.values()));
                break;

            case FPRoundingMode:
                visitor.visitFPRoundingModeMemberDecoration(structureType, member, nextEnumValue(FPRoundingMode.values()));
                break;

            case FPFastMathMode:
                visitor.visitFPFastMathModeMemberDecoration(structureType, member, new FPFastMathMode(nextWord()));
                break;

            case LinkageAttributes:
                int strSize = wordCount-2;
                visitor.visitLinkageAttributesMemberDecoration(structureType, member, nextString(strSize), nextEnumValue(LinkageType.values()));
                break;

            default:
                visitor.visitMemberDecoration(structureType, member, decoration);
                break;
        }
    }

    private String nextString(int wordCount) throws IOException {
        byte[] data = new byte[wordCount*4];
        for (int i = 0; i < wordCount; i++) {
            long word = nextWord();

            // characters are encoded in little endian
            byte b3 = (byte) (word >> 24 & 0xFF);
            byte b2 = (byte) (word >> 16 & 0xFF);
            byte b1 = (byte) (word >> 8 & 0xFF);
            byte b0 = (byte) (word >> 0 & 0xFF);

            data[i*4] = b0;

            if(b0 == 0)
                break;
            data[i*4+1] = b1;
            if(b1 == 0)
                break;
            data[i*4+2] = b2;
            if(b2 == 0)
                break;
            data[i*4+3] = b3;
            if(b3 == 0)
                break;
        }
        if(data[data.length-1] != '\0') {
            throw new IOException("Invalid string, not null terminated");
        }
        // -1 to remove the null character
        return new String(data, 0, data.length-1, "UTF-8").replace("\0", "");
    }

    private <T> T nextEnumValue(T[] values) throws IOException {
        long index = nextWord();
        return values[(int) index];
    }

    private Type nextType(Map<Long, Type> types) throws IOException {
        long id = nextWord();
        if(types.containsKey(id))
            return types.get(id);
        return new Type("Waiting"+id);
    }

    private boolean toBoolean(long value) {
        if(value != 0 && value != 1)
            throw new IllegalArgumentException("Must be 0 or 1 to convert to boolean (was "+value+")");
        return value != 0;
    }

    private SBMCodeVisitor newCodeVisitor() {
        return new CodeCollector();
    }
}

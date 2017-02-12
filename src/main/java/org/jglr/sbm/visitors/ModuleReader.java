package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;
import org.jglr.sbm.modes.InvocationsExecutionMode;
import org.jglr.sbm.modes.OutputVerticesExecutionMode;
import org.jglr.sbm.modes.SizeExecutionMode;
import org.jglr.sbm.modes.VecTypeHintExecutionMode;
import org.jglr.sbm.types.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModuleReader implements ModuleVisitor, Opcodes {

    public static final long SPIRV_MAGIC_NUMBER = 0x07230203;
    private final int[] input;
    private final ByteOrder endianness;
    private int position;

    public ModuleReader(byte[] input) throws IOException {
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

    public HeaderVisitor visitHeader() throws IOException {
        position = 4; // skip the magic number (4 bytes)
        HeaderVisitor visitor = newHeaderVisitor();
        visitor.visitSpirVersion(nextWord());
        visitor.visitGeneratorMagicNumber(nextWord());
        visitor.visitBound(nextWord());
        visitor.visitInstructionSchema(nextWord());
        return visitor;
    }

    public HeaderVisitor newHeaderVisitor() throws IOException {
        return new HeaderCollector();
    }

    public CodeVisitor visitCode() throws IOException {
        position = 5*4; // skip the header (20 bytes long)
        CodeVisitor visitor = newCodeVisitor();
        while (position < input.length) {
            long opcodeFull = nextWord();
            int wordCount = (int) (opcodeFull >> 16);
            int opcodeID = (int) (opcodeFull & 0xFFFF);
            switch (opcodeID) {
                case OpNop:
                    break;

                case OpUndef: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitUndef(resultType, resultID);
                }
                break;

                case OpTypeVoid: {
                    long resultID = nextWord();
                    visitor.visitVoidType(resultID);
                }
                break;

                case OpTypeBool: {
                    long resultID = nextWord();
                    visitor.visitBoolType(resultID);
                }
                break;

                case OpTypeInt: {
                    long resultID = nextWord();
                    long width = nextWord();
                    boolean isSigned = toBoolean(nextWord());
                    visitor.visitIntType(resultID, width, isSigned);
                }
                break;

                case OpTypeFloat: {
                    long resultID = nextWord();
                    long width = nextWord();
                    visitor.visitFloatType(resultID, width);
                }
                break;

                case OpTypeVec: {
                    long resultID = nextWord();
                    long componentType = nextWord();
                    long componentCount = nextWord();
                    visitor.visitVectorType(resultID, componentType, componentCount);
                }
                break;

                case OpTypeMatrix: {
                    long resultID = nextWord();
                    long columnType = nextWord();
                    long columnCount = nextWord();
                    visitor.visitMatrixType(resultID, columnType, columnCount);
                }
                break;

                case OpTypeImage: {
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

                case OpTypeSampler: {
                    long resultID = nextWord();
                    visitor.visitSamplerType(resultID);
                }
                break;

                case OpTypeSampledImage: {
                    long resultID = nextWord();
                    long imageType = nextWord();
                    visitor.visitSampledImageType(resultID, imageType);
                }
                break;

                case OpTypeArray: {
                    long resultID = nextWord();
                    long elementType = nextWord();
                    long length = nextWord();
                    visitor.visitArrayType(resultID, elementType, length);
                }
                break;

                case OpTypeRuntimeArray: {
                    long resultID = nextWord();
                    long elementType = nextWord();
                    visitor.visitRuntimeArrayType(resultID, elementType);
                }
                break;

                case OpTypeStruct: {
                    long resultID = nextWord();
                    long[] structMembers = nextWords(wordCount - 2);
                    visitor.visitStructType(resultID, structMembers);
                }
                break;

                case OpTypeOpaque: {
                    long resultID = nextWord();
                    String name = nextString(wordCount - 2);
                    visitor.visitOpaqueType(resultID, name);
                }
                break;

                case OpTypePointer: {
                    long resultID = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    long type = nextWord();
                    visitor.visitPointerType(resultID, storageClass, type);
                }
                break;

                case OpTypeFunction: {
                    long resultID = nextWord();
                    long returnType = nextWord();
                    int parameterCount = wordCount - 3;
                    long[] parameters = nextWords(parameterCount);
                    visitor.visitFunctionType(resultID, returnType, parameters);
                }
                break;

                case OpTypeEvent: {
                    long resultID = nextWord();
                    visitor.visitEventType(resultID);
                }
                break;

                case OpTypeDeviceEvent: {
                    long resultID = nextWord();
                    visitor.visitDeviceEventType(resultID);
                }
                break;

                case OpTypeReservedID: {
                    long resultID = nextWord();
                    visitor.visitReserveIDType(resultID);
                }
                break;

                case OpTypeQueue: {
                    long resultID = nextWord();
                    visitor.visitQueueType(resultID);
                }
                break;

                case OpTypePipe: {
                    long resultID = nextWord();
                    AccessQualifier qualifier = nextEnumValue(AccessQualifier.values());
                    visitor.visitPipeType(resultID, qualifier);
                }
                break;

                case OpTypeForwardPointer: {
                    long pointerType = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    visitor.visitForwardType(pointerType, storageClass);
                }
                break;

                case OpConstantFalse:
                case OpConstantTrue: {
                    long type = nextWord();
                    long resultID = nextWord();
                    if (opcodeID == OpConstantFalse)
                        visitor.visitFalseConstant(type, resultID);
                    else
                        visitor.visitTrueConstant(type, resultID);
                }
                break;

                case OpSource: {
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

                case OpSourceContinued: {
                    String source = nextString();
                    visitor.visitSourceContinued(source);
                }
                break;

                case OpSourceExtension: {
                    String source = nextString(wordCount - 1);
                    visitor.visitSourceExtension(source);
                }
                break;

                case OpName: {
                    long target = nextWord();
                    int stringSize = wordCount - 2;
                    String name = nextString(stringSize);
                    visitor.visitName(target, name);
                }
                break;

                case OpMemberName: {
                    long type = nextWord();
                    long target = nextWord();
                    int stringSize = wordCount - 3;
                    String name = nextString(stringSize);
                    visitor.visitMemberName(type, target, name);
                }
                break;

                case OpString: {
                    long resultID = nextWord();
                    String value = nextString(wordCount - 2);
                    visitor.visitString(resultID, value);
                }
                break;

                case OpLine: {
                    long filenameID = nextWord();
                    long line = nextWord();
                    long column = nextWord();
                    visitor.visitLine(filenameID, line, column);
                }
                break;

                case OpDecorate: {
                    long target = nextWord();
                    Decoration decoration = nextEnumValue(Decoration.values());
                    visitDecoration(visitor, decoration, target, wordCount);
                }
                break;

                case OpMemberDecorate: {
                    long structureType = nextWord();
                    long member = nextWord();
                    Decoration decoration = nextEnumValue(Decoration.values());
                    visitMemberDecoration(visitor, decoration, structureType, member, wordCount);
                }
                break;

                case OpEntryPoint: {
                    int savedPosition = position;
                    ExecutionModel model = nextEnumValue(ExecutionModel.values());
                    long entryPoint = nextWord();
                    String name = nextString();
                    int strSize = (position - savedPosition) / 4;
                    int interfaceCount = wordCount - strSize - 1;
                    long[] interfaces = nextWords(interfaceCount);
                    visitor.visitEntryPoint(model, entryPoint, name, interfaces);
                }
                break;

                case OpExtension: {
                    int strSize = wordCount - 1;
                    String extension = nextString(strSize);
                    visitor.visitExtension(extension);
                }
                break;

                case OpCapability: {
                    Capability cap = nextEnumValue(Capability.values());
                    visitor.visitCapability(cap);
                }
                break;

                case OpExtInstImport: {
                    long resultID = nextWord();
                    String name = nextString(wordCount - 2);
                    visitor.visitExtendedInstructionSetImport(resultID, name);
                }
                break;

                case OpExtInst: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long set = nextWord();
                    long instruction = nextWord();
                    int operandCount = wordCount - 5;
                    long[] operands = nextWords(operandCount);
                    visitor.visitExecExtendedInstruction(resultType, resultID, set, instruction, operands);
                }
                break;

                case OpMemoryModel: {
                    AddressingModel addressingModel = nextEnumValue(AddressingModel.values());
                    MemoryModel memoryModel = nextEnumValue(MemoryModel.values());
                    visitor.visitMemoryModel(addressingModel, memoryModel);
                }
                break;

                case OpVariable: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    StorageClass storageClass = nextEnumValue(StorageClass.values());
                    long initializer = -1;
                    if (wordCount > 4)
                        initializer = nextWord();
                    visitor.visitVariable(resultType, resultID, storageClass, initializer);
                }
                break;

                case OpConstant: {
                    long type = nextWord();
                    long resultID = nextWord();
                    long[] bitPattern = nextWords(wordCount - 3);
                    visitor.visitConstant(type, resultID, bitPattern);
                }
                break;

                case OpFunction: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    FunctionControl control = new FunctionControl(nextWord());
                    long funcType = nextWord();
                    visitor.visitFunction(resultType, resultID, control, funcType);
                }
                break;

                case OpFunctionEnd: {
                    visitor.visitFunctionEnd();
                }
                break;

                case OpAccessChain: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long base = nextWord();
                    long[] indexes = nextWords(wordCount-4);
                    visitor.visitAccessChain(resultType, resultID, base, indexes);
                }
                break;

                case OpStore: {
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

                case OpLabel: {
                    long resultID = nextWord();
                    visitor.visitLabel(resultID);
                }
                break;

                case OpReturn: {
                    visitor.visitReturn();
                }
                break;

                case OpLoad: {
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

                case OpExecutionMode: {
                    long entryPoint = nextWord();
                    ExecutionMode.Type type = nextEnumValue(ExecutionMode.Type.values());
                    ExecutionMode mode = readMode(type, wordCount-3);
                    visitor.visitExecutionMode(entryPoint, mode);
                }
                break;

                case OpImageSampleImplicitLod: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long sampledImage = nextWord();
                    long coordinate = nextWord();
                    ImageOperands operands = new ImageOperands(0);
                    if(wordCount > 5) {
                        operands.setFromMask(nextWord());
                        int count = operands.getOperandCount();
                        long[] operandValues = nextWords(count);
                        Map<Integer, long[]> splitOperands = new HashMap<>();
                        operands.splitOperands(operandValues, splitOperands);
                        visitor.visitImageSampleImplicitLod(resultType, resultID, sampledImage, coordinate, operands, splitOperands);
                    } else {
                        visitor.visitImageSampleImplicitLod(resultType, resultID, sampledImage, coordinate, operands, Collections.emptyMap());
                    }
                }
                break;

                case OpKill:
                    visitor.visitKill();
                    break;

                case OpReturnValue:
                    visitor.visitReturnValue(nextWord());
                    break;

                case OpNoLine:
                    visitor.visitNoLine();
                    break;

                case OpFunctionCall: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long functionID = nextWord();
                    long[] arguments = nextWords(wordCount - 4);
                    visitor.visitFunctionCall(resultType, resultID, functionID, arguments);
                }
                break;

                case OpCopyMemory: {
                    long targetID = nextWord();
                    long sourceID = nextWord();
                    MemoryAccess access = new MemoryAccess(0);
                    if(wordCount >= 3) {
                        access.setFromMask(nextWord());
                    }
                    visitor.visitCopyMemory(targetID, sourceID, access);
                }
                break;

                case OpCopyMemorySized: {
                    long targetID = nextWord();
                    long sourceID = nextWord();
                    long size = nextWord();
                    MemoryAccess access = new MemoryAccess(0);
                    if(wordCount >= 4) {
                        access.setFromMask(nextWord());
                    }
                    visitor.visitCopyMemorySized(targetID, sourceID, size, access);
                }
                break;

                case OpModuleProcessed: {
                    String process = nextString();
                    visitor.visitModuleProcessed(process);
                }
                break;

                case OpDecorationGroup: {
                    long decorationGroup = nextWord();
                    visitor.visitDecorationGroup(decorationGroup);
                }
                break;

                case OpGroupDecorate: {
                    long decorationGroup = nextWord();
                    long[] targets = nextWords(wordCount-2);
                    visitor.visitGroupDecoration(decorationGroup, targets);
                }
                break;

                case OpGroupMemberDecorate: {
                    long decorationGroup = nextWord();
                    long[] targets = nextWords(wordCount-2);
                    visitor.visitGroupMemberDecoration(decorationGroup, targets);
                }
                break;

                case OpTypeNamedBarrier: {
                    long resultID = nextWord();
                    visitor.visitNamedBarrierType(resultID);
                }
                break;

                case OpConstantComposite: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long[] constituents = nextWords(wordCount-3);
                    visitor.visitConstantComposite(resultType, resultID, constituents);
                }
                break;

                case OpConstantSampler: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    SamplerAddressingMode mode = nextEnumValue(SamplerAddressingMode.values());
                    boolean normalized = nextWord() == 1;
                    SamplerFilterMode filter = nextEnumValue(SamplerFilterMode.values());
                    visitor.visitConstantSampler(resultType, resultID, mode, normalized, filter);
                }
                break;

                case OpConstantNull: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitConstantNull(resultType, resultID);
                }
                break;

                case OpSpecConstantTrue: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitSpecConstantBool(resultType, resultID, true);
                }
                break;

                case OpSpecConstantFalse: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitSpecConstantBool(resultType, resultID, false);
                }
                break;

                case OpSpecConstant: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long[] value = nextWords(wordCount - 3);
                    visitor.visitSpecConstant(resultType, resultID, value);
                }
                break;

                case OpSpecConstantComposite: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long[] constituents = nextWords(wordCount - 3);
                    visitor.visitSpecConstantComposite(resultType, resultID, constituents);
                }
                break;

                case OpSpecConstantOp: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long opcode = nextWord();
                    long[] operands = nextWords(wordCount-4);
                    visitor.visitSpecConstantOp(resultType, resultID, opcode, operands);
                }
                break;

                case OpFunctionParameter: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    visitor.visitFunctionParameter(resultType, resultID);
                }
                break;

                case OpTypePipeStorage: {
                    long resultID = nextWord();
                    visitor.visitPipeStorageType(resultID);
                }
                break;

                case OpCompositeConstruct: {
                    long resultType = nextWord();
                    long resultID = nextWord();
                    long[] constituents = nextWords(wordCount-3);
                    visitor.visitCompositeConstruct(resultType, resultID, constituents);
                }
                break;

                case OpSNegate: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long operand = nextWord();
                    visitor.visitSNegate(resultTypeID, resultID, operand);
                }
                break;

                case OpFNegate: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long operand = nextWord();
                    visitor.visitFNegate(resultTypeID, resultID, operand);
                }
                break;

                case OpIAdd: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitIAdd(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFAdd: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFAdd(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpISub: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitISub(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFSub: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFSub(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpIMul: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitIMul(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFMul: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFMul(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpUDiv: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitUDiv(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpSDiv: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitSDiv(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFDiv: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFDiv(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpUMod: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitUMod(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpSRem: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitSRem(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpSMod: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitSMod(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFRem: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFRem(resultTypeID, resultID, leftID, rightID);
                }
                break;

                case OpFMod: {
                    long resultTypeID = nextWord();
                    long resultID = nextWord();
                    long leftID = nextWord();
                    long rightID = nextWord();
                    visitor.visitFMod(resultTypeID, resultID, leftID, rightID);
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

    private long[] nextWords(int count) throws IOException {
        long[] result = new long[count];
        for (int i = 0; i < count; i++) {
            result[i] = nextWord();
        }
        return result;
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

    private void visitDecoration(CodeVisitor visitor, Decoration decoration, long target, int wordCount) throws IOException {
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

    private void visitMemberDecoration(CodeVisitor visitor, Decoration decoration, long structureType, long member, int wordCount) throws IOException {
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
            byte b3 = (byte) ((word >> 24) & 0xFF);
            byte b2 = (byte) ((word >> 16) & 0xFF);
            byte b1 = (byte) ((word >> 8) & 0xFF);
            byte b0 = (byte) ((word >> 0) & 0xFF);

            data[i*4] = b0;
            data[i*4+1] = b1;
            data[i*4+2] = b2;
            data[i*4+3] = b3;
        }
        int len = data.length-1;
        for (; len >= 0; len--) {
            if(data[len] != 0)
                break;
        }
        // -1 to remove the null character
        return new String(data, 0, len+1, "UTF-8").replace("\0", "");
    }

    private <T> T nextEnumValue(T[] values) throws IOException {
        long index = nextWord();
        return values[((int) index) & 0xFFFF];
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

    public CodeVisitor newCodeVisitor() throws IOException {
        return new CodeCollector();
    }
}

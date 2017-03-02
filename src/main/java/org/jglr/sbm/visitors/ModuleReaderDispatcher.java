package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ModuleReaderDispatcher implements Opcodes {

    private final ModuleReader reader;

    ModuleReaderDispatcher(ModuleReader reader) {
        this.reader = reader;
    }

    private boolean toBoolean(long value) {
        if(value != 0 && value != 1)
            throw new IllegalArgumentException("Must be 0 or 1 to convert to boolean (was "+value+")");
        return value != 0;
    }
    
    void dispatch(int opcodeID, int wordCount, CodeVisitor visitor) throws IOException {
        switch (opcodeID) {
            case OpNop:
                // TODO: visitNop();
                break;

            case OpUndef: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                visitor.visitUndef(resultType, resultID);
            }
            break;

            case OpTypeVoid: {
                long resultID = reader.nextWord();
                visitor.visitVoidType(resultID);
            }
            break;

            case OpTypeBool: {
                long resultID = reader.nextWord();
                visitor.visitBoolType(resultID);
            }
            break;

            case OpTypeInt: {
                long resultID = reader.nextWord();
                long width = reader.nextWord();
                boolean isSigned = toBoolean(reader.nextWord());
                visitor.visitIntType(resultID, width, isSigned);
            }
            break;

            case OpTypeFloat: {
                long resultID = reader.nextWord();
                long width = reader.nextWord();
                visitor.visitFloatType(resultID, width);
            }
            break;

            case OpTypeVector: {
                long resultID = reader.nextWord();
                long componentType = reader.nextWord();
                long componentCount = reader.nextWord();
                visitor.visitVectorType(resultID, componentType, componentCount);
            }
            break;

            case OpTypeMatrix: {
                long resultID = reader.nextWord();
                long columnType = reader.nextWord();
                long columnCount = reader.nextWord();
                visitor.visitMatrixType(resultID, columnType, columnCount);
            }
            break;

            case OpTypeImage: {
                long resultID = reader.nextWord();
                long sampledType = reader.nextWord();
                Dimensionality dimensionality = reader.nextEnumValue(Dimensionality.values());
                ImageDepth depth = reader.nextEnumValue(ImageDepth.values());
                boolean arrayed = toBoolean(reader.nextWord());
                boolean multisampled = toBoolean(reader.nextWord());
                Sampling sampling = reader.nextEnumValue(Sampling.values());
                ImageFormat format = reader.nextEnumValue(ImageFormat.values());
                AccessQualifier qualifier = null;
                if(wordCount > 9)
                    qualifier = reader.nextEnumValue(AccessQualifier.values());
                visitor.visitImageType(resultID, sampledType, dimensionality, depth, arrayed, multisampled, sampling, format, qualifier);
            }
            break;

            case OpTypeSampler: {
                long resultID = reader.nextWord();
                visitor.visitSamplerType(resultID);
            }
            break;

            case OpTypeSampledImage: {
                long resultID = reader.nextWord();
                long imageType = reader.nextWord();
                visitor.visitSampledImageType(resultID, imageType);
            }
            break;

            case OpTypeArray: {
                long resultID = reader.nextWord();
                long elementType = reader.nextWord();
                long length = reader.nextWord();
                visitor.visitArrayType(resultID, elementType, length);
            }
            break;

            case OpTypeRuntimeArray: {
                long resultID = reader.nextWord();
                long elementType = reader.nextWord();
                visitor.visitRuntimeArrayType(resultID, elementType);
            }
            break;

            case OpTypeStruct: {
                long resultID = reader.nextWord();
                long[] structMembers = reader.nextWords(wordCount - 2);
                visitor.visitStructType(resultID, structMembers);
            }
            break;

            case OpTypeOpaque: {
                long resultID = reader.nextWord();
                String name = reader.nextString(wordCount - 2);
                visitor.visitOpaqueType(resultID, name);
            }
            break;

            case OpTypePointer: {
                long resultID = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                long type = reader.nextWord();
                visitor.visitPointerType(resultID, storageClass, type);
            }
            break;

            case OpTypeFunction: {
                long resultID = reader.nextWord();
                long returnType = reader.nextWord();
                int parameterCount = wordCount - 3;
                long[] parameters = reader.nextWords(parameterCount);
                visitor.visitFunctionType(resultID, returnType, parameters);
            }
            break;

            case OpTypeEvent: {
                long resultID = reader.nextWord();
                visitor.visitEventType(resultID);
            }
            break;

            case OpTypeDeviceEvent: {
                long resultID = reader.nextWord();
                visitor.visitDeviceEventType(resultID);
            }
            break;

            case OpTypeReserveId: {
                long resultID = reader.nextWord();
                visitor.visitReserveIdType(resultID);
            }
            break;

            case OpTypeQueue: {
                long resultID = reader.nextWord();
                visitor.visitQueueType(resultID);
            }
            break;

            case OpTypePipe: {
                long resultID = reader.nextWord();
                AccessQualifier qualifier = reader.nextEnumValue(AccessQualifier.values());
                visitor.visitPipeType(resultID, qualifier);
            }
            break;

            case OpTypeForwardPointer: {
                long pointerType = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                visitor.visitForwardPointerType(pointerType, storageClass);
            }
            break;

            case OpConstantFalse:
            case OpConstantTrue: {
                long type = reader.nextWord();
                long resultID = reader.nextWord();
                if (opcodeID == OpConstantFalse)
                    visitor.visitConstantFalse(type, resultID);
                else
                    visitor.visitConstantTrue(type, resultID);
            }
            break;

            case OpSource: {
                SourceLanguage language = reader.nextEnumValue(SourceLanguage.values());
                long version = reader.nextWord();
                long filenameStringID = -1;
                String sourceCode = null;
                if (wordCount > 3) {
                    filenameStringID = reader.nextWord();
                    if (wordCount > 4) {
                        sourceCode = reader.nextString(wordCount - 4);
                    }
                }
                visitor.visitSource(language, version, filenameStringID, sourceCode);
            }
            break;

            case OpSourceContinued: {
                String source = reader.nextString(wordCount);
                visitor.visitSourceContinued(source);
            }
            break;

            case OpSourceExtension: {
                String source = reader.nextString(wordCount - 1);
                visitor.visitSourceExtension(source);
            }
            break;

            case OpName: {
                long target = reader.nextWord();
                int stringSize = wordCount - 2;
                String name = reader.nextString(stringSize);
                visitor.visitName(target, name);
            }
            break;

            case OpMemberName: {
                long type = reader.nextWord();
                long target = reader.nextWord();
                int stringSize = wordCount - 3;
                String name = reader.nextString(stringSize);
                visitor.visitMemberName(type, target, name);
            }
            break;

            case OpString: {
                long resultID = reader.nextWord();
                String value = reader.nextString(wordCount - 2);
                visitor.visitString(resultID, value);
            }
            break;

            case OpLine: {
                long filenameID = reader.nextWord();
                long line = reader.nextWord();
                long column = reader.nextWord();
                visitor.visitLine(filenameID, line, column);
            }
            break;

            case OpMemberDecorate: {
                long structureType = reader.nextWord();
                long member = reader.nextWord();
                Decoration decoration = reader.nextEnumValue(Decoration.values());
                reader.visitMemberDecoration(visitor, decoration, structureType, member, wordCount);
            }
            break;

            case OpEntryPoint: {
                int savedPosition = reader.position;
                ExecutionModel model = reader.nextEnumValue(ExecutionModel.values());
                long entryPoint = reader.nextWord();
                String name = reader.nextString();
                int strSize = (reader.position - savedPosition) / 4;
                int interfaceCount = wordCount - strSize - 1;
                long[] interfaces = reader.nextWords(interfaceCount);
                visitor.visitEntryPoint(model, entryPoint, name, interfaces);
            }
            break;

            case OpDecorate: {
                long target = reader.nextWord();
                Decoration decoration = reader.nextEnumValue(Decoration.values());
                reader.visitDecoration(visitor, decoration, target, wordCount);
            }
            break;

            case OpExecutionMode: {
                long entryPoint = reader.nextWord();
                ExecutionMode.Type type = reader.nextEnumValue(ExecutionMode.Type.values());
                ExecutionMode mode = reader.readMode(type);
                visitor.visitExecutionMode(entryPoint, mode);
            }
            break;

            case OpExtension: {
                int strSize = wordCount - 1;
                String extension = reader.nextString(strSize);
                visitor.visitExtension(extension);
            }
            break;

            case OpCapability: {
                Capability cap = reader.nextEnumValue(Capability.values());
                visitor.visitCapability(cap);
            }
            break;

            case OpExtInstImport: {
                long resultID = reader.nextWord();
                String name = reader.nextString(wordCount - 2);
                visitor.visitExtendedInstructionSetImport(resultID, name);
            }
            break;

            case OpExtInst: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long set = reader.nextWord();
                long instruction = reader.nextWord();
                int operandCount = wordCount - 5;
                long[] operands = reader.nextWords(operandCount);
                visitor.visitExecExtendedInstruction(resultType, resultID, set, instruction, operands);
            }
            break;

            case OpMemoryModel: {
                AddressingModel addressingModel = reader.nextEnumValue(AddressingModel.values());
                MemoryModel memoryModel = reader.nextEnumValue(MemoryModel.values());
                visitor.visitMemoryModel(addressingModel, memoryModel);
            }
            break;

            case OpVariable: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                long initializer = -1;
                if (wordCount > 4)
                    initializer = reader.nextWord();
                visitor.visitVariable(resultType, resultID, storageClass, initializer);
            }
            break;

            case OpConstant: {
                long type = reader.nextWord();
                long resultID = reader.nextWord();
                long[] bitPattern = reader.nextWords(wordCount - 3);
                visitor.visitConstant(type, resultID, bitPattern);
            }
            break;

            case OpFunction: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                FunctionControl control = new FunctionControl(reader.nextWord());
                long funcType = reader.nextWord();
                visitor.visitFunction(resultType, resultID, control, funcType);
            }
            break;

            case OpFunctionEnd: {
                visitor.visitFunctionEnd();
            }
            break;

            case OpAccessChain: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long base = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-4);
                visitor.visitAccessChain(resultType, resultID, base, indexes);
            }
            break;

            case OpStore: {
                long pointer = reader.nextWord();
                long object = reader.nextWord();
                MemoryAccess memoryAccess;
                if(wordCount > 3) {
                    memoryAccess = new MemoryAccess(reader.nextWord());
                } else {
                    memoryAccess = new MemoryAccess(0);
                }
                visitor.visitStore(pointer, object, memoryAccess);
            }
            break;

            case OpLabel: {
                long resultID = reader.nextWord();
                visitor.visitLabel(resultID);
            }
            break;

            case OpReturn: {
                visitor.visitReturn();
            }
            break;

            case OpLoad: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long pointer = reader.nextWord();
                MemoryAccess memoryAccess;
                if(wordCount > 4) {
                    memoryAccess = new MemoryAccess(reader.nextWord());
                } else {
                    memoryAccess = new MemoryAccess(0);
                }
                visitor.visitLoad(resultType, resultID, pointer, memoryAccess);
            }
            break;

            case OpImageSampleImplicitLod: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands operands = new ImageOperands(0);
                if(wordCount > 5) {
                    operands.setFromMask(reader.nextWord());
                    int count = operands.getOperandCount();
                    long[] operandValues = reader.nextWords(count);
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
                visitor.visitReturnValue(reader.nextWord());
                break;

            case OpNoLine:
                visitor.visitNoLine();
                break;

            case OpFunctionCall: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long functionID = reader.nextWord();
                long[] arguments = reader.nextWords(wordCount - 4);
                visitor.visitFunctionCall(resultType, resultID, functionID, arguments);
            }
            break;

            case OpCopyMemory: {
                long targetID = reader.nextWord();
                long sourceID = reader.nextWord();
                MemoryAccess access = new MemoryAccess(0);
                if(wordCount >= 3) {
                    access.setFromMask(reader.nextWord());
                }
                visitor.visitCopyMemory(targetID, sourceID, access);
            }
            break;

            case OpCopyMemorySized: {
                long targetID = reader.nextWord();
                long sourceID = reader.nextWord();
                long size = reader.nextWord();
                MemoryAccess access = new MemoryAccess(0);
                if(wordCount >= 4) {
                    access.setFromMask(reader.nextWord());
                }
                visitor.visitCopyMemorySized(targetID, sourceID, size, access);
            }
            break;

            case OpModuleProcessed: {
                String process = reader.nextString();
                visitor.visitModuleProcessed(process);
            }
            break;

            case OpDecorationGroup: {
                long decorationGroup = reader.nextWord();
                visitor.visitDecorationGroup(decorationGroup);
            }
            break;

            case OpGroupDecorate: {
                long decorationGroup = reader.nextWord();
                long[] targets = reader.nextWords(wordCount-2);
                visitor.visitGroupDecoration(decorationGroup, targets);
            }
            break;

            case OpGroupMemberDecorate: {
                long decorationGroup = reader.nextWord();
                long[] targets = reader.nextWords(wordCount-2);
                visitor.visitGroupMemberDecoration(decorationGroup, targets);
            }
            break;

            case OpTypeNamedBarrier: {
                long resultID = reader.nextWord();
                visitor.visitNamedBarrierType(resultID);
            }
            break;

            case OpConstantComposite: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount-3);
                visitor.visitConstantComposite(resultType, resultID, constituents);
            }
            break;

            case OpConstantSampler: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                SamplerAddressingMode mode = reader.nextEnumValue(SamplerAddressingMode.values());
                boolean normalized = reader.nextWord() == 1;
                SamplerFilterMode filter = reader.nextEnumValue(SamplerFilterMode.values());
                visitor.visitConstantSampler(resultType, resultID, mode, normalized, filter);
            }
            break;

            case OpConstantNull: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                visitor.visitConstantNull(resultType, resultID);
            }
            break;

            case OpSpecConstantTrue: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                visitor.visitSpecConstantTrue(resultType, resultID);
            }
            break;

            case OpSpecConstantFalse: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                visitor.visitSpecConstantFalse(resultType, resultID);
            }
            break;

            case OpSpecConstant: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long[] value = reader.nextWords(wordCount - 3);
                visitor.visitSpecConstant(resultType, resultID, value);
            }
            break;

            case OpSpecConstantComposite: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount - 3);
                visitor.visitSpecConstantComposite(resultType, resultID, constituents);
            }
            break;

            case OpSpecConstantOp: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long opcode = reader.nextWord();
                long[] operands = reader.nextWords(wordCount-4);
                visitor.visitSpecConstantOp(resultType, resultID, opcode, operands);
            }
            break;

            case OpFunctionParameter: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                visitor.visitFunctionParameter(resultType, resultID);
            }
            break;

            case OpTypePipeStorage: {
                long resultID = reader.nextWord();
                visitor.visitPipeStorageType(resultID);
            }
            break;

            case OpCompositeConstruct: {
                long resultType = reader.nextWord();
                long resultID = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount-3);
                visitor.visitCompositeConstruct(resultType, resultID, constituents);
            }
            break;

            case OpSNegate: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitSNegate(resultTypeID, resultID, operand);
            }
            break;

            case OpFNegate: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitFNegate(resultTypeID, resultID, operand);
            }
            break;

            case OpIAdd: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitIAdd(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFAdd: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFAdd(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpISub: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitISub(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFSub: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFSub(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpIMul: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitIMul(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFMul: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFMul(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpUDiv: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitUDiv(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpSDiv: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitSDiv(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFDiv: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFDiv(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpUMod: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitUMod(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpSRem: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitSRem(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpSMod: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitSMod(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFRem: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFRem(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpFMod: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long leftID = reader.nextWord();
                long rightID = reader.nextWord();
                visitor.visitFMod(resultTypeID, resultID, leftID, rightID);
            }
            break;

            case OpCompositeExtract: {
                long resultTypeID = reader.nextWord();
                long resultID = reader.nextWord();
                long compositeID = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-4);
                visitor.visitCompositeExtract(resultTypeID, resultID, compositeID, indexes);
            }
            break;

            default:
                System.err.println("Unhandled: " + Opcodes.getName(opcodeID) + " " + opcodeID + " / " + wordCount+" at "+reader.position);
                reader.position += (wordCount-1)*4;
                break;
        }
    }
}

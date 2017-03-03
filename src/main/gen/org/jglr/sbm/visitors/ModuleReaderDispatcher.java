// Auto-generated from org.jglr.sbm.generation.ModuleReaderDispatcherGenerator
package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModuleReaderDispatcher implements Opcodes {
    
    private ModuleReader reader;
    
    
    private boolean toBoolean(long value) {
        if(value != 0 && value != 1) throw new IllegalArgumentException("Must be 0 or 1 to convert to boolean (was "+value+")");
        return value != 0;
    }
    
    public ModuleReaderDispatcher(ModuleReader reader) {
        this.reader = reader;
    }
    
    public void dispatch(int opcodeID, int wordCount, CodeVisitor visitor) throws IOException {
        switch (opcodeID) {
            case OpNop: {
                visitor.visitNop();
            }
            break;
            
            case OpUndef: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitUndef(resultType, result);
            }
            break;
            
            case OpSizeOf: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                visitor.visitSizeOf(resultType, result, pointer);
            }
            break;
            
            case OpSourceContinued: {
                String source = reader.nextString(wordCount-1);
                visitor.visitSourceContinued(source);
            }
            break;
            
            case OpSource: {
                SourceLanguage sourceLanguage = reader.nextEnumValue(SourceLanguage.values());
                long version = reader.nextWord();
                long optionalLong = -1;
                if(wordCount > 3) {
                    optionalLong = reader.nextWord();
                    
                }
                String optionalString = null;
                if(wordCount > 4) {
                    optionalString = reader.nextString(wordCount-4);
                    
                }
                visitor.visitSource(sourceLanguage, version, optionalLong, optionalString);
            }
            break;
            
            case OpSourceExtension: {
                String extension = reader.nextString(wordCount-1);
                visitor.visitSourceExtension(extension);
            }
            break;
            
            case OpName: {
                long target = reader.nextWord();
                String name = reader.nextString(wordCount-2);
                visitor.visitName(target, name);
            }
            break;
            
            case OpMemberName: {
                long type = reader.nextWord();
                long member = reader.nextWord();
                String name = reader.nextString(wordCount-3);
                visitor.visitMemberName(type, member, name);
            }
            break;
            
            case OpString: {
                long result = reader.nextWord();
                String string = reader.nextString(wordCount-2);
                visitor.visitString(result, string);
            }
            break;
            
            case OpLine: {
                long file = reader.nextWord();
                long line = reader.nextWord();
                long column = reader.nextWord();
                visitor.visitLine(file, line, column);
            }
            break;
            
            case OpNoLine: {
                visitor.visitNoLine();
            }
            break;
            
            case OpModuleProcessed: {
                String process = reader.nextString(wordCount-1);
                visitor.visitModuleProcessed(process);
            }
            break;
            
            case OpDecorationGroup: {
                long result = reader.nextWord();
                visitor.visitDecorationGroup(result);
            }
            break;
            
            case OpExtension: {
                String name = reader.nextString(wordCount-1);
                visitor.visitExtension(name);
            }
            break;
            
            case OpExtInstImport: {
                long result = reader.nextWord();
                String name = reader.nextString(wordCount-2);
                visitor.visitExtendedInstructionSetImport(result, name);
            }
            break;
            
            case OpExtInst: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long set = reader.nextWord();
                long instruction = reader.nextWord();
                long[] operands = reader.nextWords(wordCount-5);
                visitor.visitExecExtendedInstruction(resultType, result, set, instruction, operands);
            }
            break;
            
            case OpMemoryModel: {
                AddressingModel addressingModel = reader.nextEnumValue(AddressingModel.values());
                MemoryModel memoryModel = reader.nextEnumValue(MemoryModel.values());
                visitor.visitMemoryModel(addressingModel, memoryModel);
            }
            break;
            
            case OpCapability: {
                Capability capability = reader.nextEnumValue(Capability.values());
                visitor.visitCapability(capability);
            }
            break;
            
            case OpTypeVoid: {
                long result = reader.nextWord();
                visitor.visitVoidType(result);
            }
            break;
            
            case OpTypeBool: {
                long result = reader.nextWord();
                visitor.visitBoolType(result);
            }
            break;
            
            case OpTypeInt: {
                long result = reader.nextWord();
                long width = reader.nextWord();
                boolean signedness = toBoolean(reader.nextWord());
                visitor.visitIntType(result, width, signedness);
            }
            break;
            
            case OpTypeFloat: {
                long result = reader.nextWord();
                long width = reader.nextWord();
                visitor.visitFloatType(result, width);
            }
            break;
            
            case OpTypeVector: {
                long result = reader.nextWord();
                long componentType = reader.nextWord();
                long count = reader.nextWord();
                visitor.visitVectorType(result, componentType, count);
            }
            break;
            
            case OpTypeMatrix: {
                long result = reader.nextWord();
                long columnType = reader.nextWord();
                long count = reader.nextWord();
                visitor.visitMatrixType(result, columnType, count);
            }
            break;
            
            case OpTypeImage: {
                long result = reader.nextWord();
                long sampledType = reader.nextWord();
                Dimensionality dim = reader.nextEnumValue(Dimensionality.values());
                ImageDepth depth = reader.nextEnumValue(ImageDepth.values());
                boolean arrayed = toBoolean(reader.nextWord());
                boolean mS = toBoolean(reader.nextWord());
                Sampling sampled = reader.nextEnumValue(Sampling.values());
                ImageFormat imageFormat = reader.nextEnumValue(ImageFormat.values());
                AccessQualifier optionalAccessQualifier = null;
                if(wordCount > 9) {
                    optionalAccessQualifier = reader.nextEnumValue(AccessQualifier.values());
                    
                }
                visitor.visitImageType(result, sampledType, dim, depth, arrayed, mS, sampled, imageFormat, optionalAccessQualifier);
            }
            break;
            
            case OpTypeSampler: {
                long result = reader.nextWord();
                visitor.visitSamplerType(result);
            }
            break;
            
            case OpTypeSampledImage: {
                long result = reader.nextWord();
                long imageType = reader.nextWord();
                visitor.visitSampledImageType(result, imageType);
            }
            break;
            
            case OpTypeArray: {
                long result = reader.nextWord();
                long elementType = reader.nextWord();
                long length = reader.nextWord();
                visitor.visitArrayType(result, elementType, length);
            }
            break;
            
            case OpTypeRuntimeArray: {
                long result = reader.nextWord();
                long elementType = reader.nextWord();
                visitor.visitRuntimeArrayType(result, elementType);
            }
            break;
            
            case OpTypeStruct: {
                long result = reader.nextWord();
                long[] members = reader.nextWords(wordCount-2);
                visitor.visitStructType(result, members);
            }
            break;
            
            case OpTypeOpaque: {
                long result = reader.nextWord();
                String type = reader.nextString(wordCount-2);
                visitor.visitOpaqueType(result, type);
            }
            break;
            
            case OpTypePointer: {
                long result = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                long type = reader.nextWord();
                visitor.visitPointerType(result, storageClass, type);
            }
            break;
            
            case OpTypeFunction: {
                long result = reader.nextWord();
                long returnType = reader.nextWord();
                long[] parameters = reader.nextWords(wordCount-3);
                visitor.visitFunctionType(result, returnType, parameters);
            }
            break;
            
            case OpTypeEvent: {
                long result = reader.nextWord();
                visitor.visitEventType(result);
            }
            break;
            
            case OpTypeDeviceEvent: {
                long result = reader.nextWord();
                visitor.visitDeviceEventType(result);
            }
            break;
            
            case OpTypeReserveId: {
                long result = reader.nextWord();
                visitor.visitReserveIdType(result);
            }
            break;
            
            case OpTypeQueue: {
                long result = reader.nextWord();
                visitor.visitQueueType(result);
            }
            break;
            
            case OpTypePipe: {
                long result = reader.nextWord();
                AccessQualifier qualifier = reader.nextEnumValue(AccessQualifier.values());
                visitor.visitPipeType(result, qualifier);
            }
            break;
            
            case OpTypeForwardPointer: {
                long pointerType = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                visitor.visitForwardPointerType(pointerType, storageClass);
            }
            break;
            
            case OpTypePipeStorage: {
                long result = reader.nextWord();
                visitor.visitPipeStorageType(result);
            }
            break;
            
            case OpTypeNamedBarrier: {
                long result = reader.nextWord();
                visitor.visitNamedBarrierType(result);
            }
            break;
            
            case OpConstantTrue: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitConstantTrue(resultType, result);
            }
            break;
            
            case OpConstantFalse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitConstantFalse(resultType, result);
            }
            break;
            
            case OpConstant: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] value = reader.nextWords(wordCount-3);
                visitor.visitConstant(resultType, result, value);
            }
            break;
            
            case OpConstantComposite: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount-3);
                visitor.visitConstantComposite(resultType, result, constituents);
            }
            break;
            
            case OpConstantSampler: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                SamplerAddressingMode samplerAddressingMode = reader.nextEnumValue(SamplerAddressingMode.values());
                boolean param = toBoolean(reader.nextWord());
                SamplerFilterMode samplerFilterMode = reader.nextEnumValue(SamplerFilterMode.values());
                visitor.visitConstantSampler(resultType, result, samplerAddressingMode, param, samplerFilterMode);
            }
            break;
            
            case OpConstantNull: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitConstantNull(resultType, result);
            }
            break;
            
            case OpSpecConstantTrue: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitSpecConstantTrue(resultType, result);
            }
            break;
            
            case OpSpecConstantFalse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitSpecConstantFalse(resultType, result);
            }
            break;
            
            case OpSpecConstant: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] value = reader.nextWords(wordCount-3);
                visitor.visitSpecConstant(resultType, result, value);
            }
            break;
            
            case OpSpecConstantComposite: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount-3);
                visitor.visitSpecConstantComposite(resultType, result, constituents);
            }
            break;
            
            case OpSpecConstantOp: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long opcode = reader.nextWord();
                long[] operands = reader.nextWords(wordCount-4);
                visitor.visitSpecConstantOp(resultType, result, opcode, operands);
            }
            break;
            
            case OpVariable: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                StorageClass storageClass = reader.nextEnumValue(StorageClass.values());
                long optionalLong = -1;
                if(wordCount > 4) {
                    optionalLong = reader.nextWord();
                    
                }
                visitor.visitVariable(resultType, result, storageClass, optionalLong);
            }
            break;
            
            case OpImageTexelPointer: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                long sample = reader.nextWord();
                visitor.visitImageTexelPointer(resultType, result, image, coordinate, sample);
            }
            break;
            
            case OpLoad: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                MemoryAccess optionalMemoryAccess = new MemoryAccess(0);if(wordCount > 4) {
                    optionalMemoryAccess = new MemoryAccess(reader.nextWord());
                    
                }
                visitor.visitLoad(resultType, result, pointer, optionalMemoryAccess);
            }
            break;
            
            case OpStore: {
                long pointer = reader.nextWord();
                long object = reader.nextWord();
                MemoryAccess optionalMemoryAccess = new MemoryAccess(0);if(wordCount > 3) {
                    optionalMemoryAccess = new MemoryAccess(reader.nextWord());
                    
                }
                visitor.visitStore(pointer, object, optionalMemoryAccess);
            }
            break;
            
            case OpCopyMemory: {
                long target = reader.nextWord();
                long source = reader.nextWord();
                MemoryAccess optionalMemoryAccess = new MemoryAccess(0);if(wordCount > 3) {
                    optionalMemoryAccess = new MemoryAccess(reader.nextWord());
                    
                }
                visitor.visitCopyMemory(target, source, optionalMemoryAccess);
            }
            break;
            
            case OpCopyMemorySized: {
                long target = reader.nextWord();
                long source = reader.nextWord();
                long size = reader.nextWord();
                MemoryAccess optionalMemoryAccess = new MemoryAccess(0);if(wordCount > 4) {
                    optionalMemoryAccess = new MemoryAccess(reader.nextWord());
                    
                }
                visitor.visitCopyMemorySized(target, source, size, optionalMemoryAccess);
            }
            break;
            
            case OpAccessChain: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-4);
                visitor.visitAccessChain(resultType, result, base, indexes);
            }
            break;
            
            case OpInBoundsAccessChain: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-4);
                visitor.visitInBoundsAccessChain(resultType, result, base, indexes);
            }
            break;
            
            case OpPtrAccessChain: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long element = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-5);
                visitor.visitPtrAccessChain(resultType, result, base, element, indexes);
            }
            break;
            
            case OpArrayLength: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long structure = reader.nextWord();
                long member = reader.nextWord();
                visitor.visitArrayLength(resultType, result, structure, member);
            }
            break;
            
            case OpGenericPtrMemSemantics: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                visitor.visitGenericPtrMemSemantics(resultType, result, pointer);
            }
            break;
            
            case OpInBoundsPtrAccessChain: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long element = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-5);
                visitor.visitInBoundsPtrAccessChain(resultType, result, base, element, indexes);
            }
            break;
            
            case OpFunction: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                FunctionControl functionControl = new FunctionControl(reader.nextWord());
                long functionType = reader.nextWord();
                visitor.visitFunction(resultType, result, functionControl, functionType);
            }
            break;
            
            case OpFunctionParameter: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitFunctionParameter(resultType, result);
            }
            break;
            
            case OpFunctionEnd: {
                visitor.visitFunctionEnd();
            }
            break;
            
            case OpFunctionCall: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long function = reader.nextWord();
                long[] arguments = reader.nextWords(wordCount-4);
                visitor.visitFunctionCall(resultType, result, function, arguments);
            }
            break;
            
            case OpSampledImage: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long sampler = reader.nextWord();
                visitor.visitSampledImage(resultType, result, image, sampler);
            }
            break;
            
            case OpImageSampleImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSampleImplicitLod(resultType, result, sampledImage, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSampleExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 7) {
                    optionalLongArray = reader.nextWords(wordCount-7);
                    
                }
                visitor.visitImageSampleExplicitLod(resultType, result, sampledImage, coordinate, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSampleDrefImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSampleDrefImplicitLod(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSampleDrefExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 8) {
                    optionalLongArray = reader.nextWords(wordCount-8);
                    
                }
                visitor.visitImageSampleDrefExplicitLod(resultType, result, sampledImage, coordinate, dref, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSampleProjImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSampleProjImplicitLod(resultType, result, sampledImage, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSampleProjExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 7) {
                    optionalLongArray = reader.nextWords(wordCount-7);
                    
                }
                visitor.visitImageSampleProjExplicitLod(resultType, result, sampledImage, coordinate, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSampleProjDrefImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSampleProjDrefImplicitLod(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSampleProjDrefExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 8) {
                    optionalLongArray = reader.nextWords(wordCount-8);
                    
                }
                visitor.visitImageSampleProjDrefExplicitLod(resultType, result, sampledImage, coordinate, dref, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageFetch: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageFetch(resultType, result, image, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageGather: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long component = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageGather(resultType, result, sampledImage, coordinate, component, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageDrefGather: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageDrefGather(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageRead: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageRead(resultType, result, image, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageWrite: {
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                long texel = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 4) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 5) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageWrite(image, coordinate, texel, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImage: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                visitor.visitImage(resultType, result, sampledImage);
            }
            break;
            
            case OpImageQueryFormat: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                visitor.visitImageQueryFormat(resultType, result, image);
            }
            break;
            
            case OpImageQueryOrder: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                visitor.visitImageQueryOrder(resultType, result, image);
            }
            break;
            
            case OpImageQuerySizeLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long levelOfDetail = reader.nextWord();
                visitor.visitImageQuerySizeLod(resultType, result, image, levelOfDetail);
            }
            break;
            
            case OpImageQuerySize: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                visitor.visitImageQuerySize(resultType, result, image);
            }
            break;
            
            case OpImageQueryLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                visitor.visitImageQueryLod(resultType, result, sampledImage, coordinate);
            }
            break;
            
            case OpImageQueryLevels: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                visitor.visitImageQueryLevels(resultType, result, image);
            }
            break;
            
            case OpImageQuerySamples: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                visitor.visitImageQuerySamples(resultType, result, image);
            }
            break;
            
            case OpImageSparseSampleImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseSampleImplicitLod(resultType, result, sampledImage, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseSampleExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 7) {
                    optionalLongArray = reader.nextWords(wordCount-7);
                    
                }
                visitor.visitImageSparseSampleExplicitLod(resultType, result, sampledImage, coordinate, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSparseSampleDrefImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseSampleDrefImplicitLod(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseSampleDrefExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 8) {
                    optionalLongArray = reader.nextWords(wordCount-8);
                    
                }
                visitor.visitImageSparseSampleDrefExplicitLod(resultType, result, sampledImage, coordinate, dref, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSparseSampleProjImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseSampleProjImplicitLod(resultType, result, sampledImage, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseSampleProjExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 7) {
                    optionalLongArray = reader.nextWords(wordCount-7);
                    
                }
                visitor.visitImageSparseSampleProjExplicitLod(resultType, result, sampledImage, coordinate, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSparseSampleProjDrefImplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseSampleProjDrefImplicitLod(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseSampleProjDrefExplicitLod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands imageOperands = new ImageOperands(reader.nextWord());
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_imageOperands = imageOperands.getOperandCount();
                    long[] values_imageOperands = reader.nextWords(count_imageOperands);
                    splitOperands = new HashMap<>();
                    imageOperands.splitOperands(values_imageOperands, splitOperands);
                }
                long[] optionalLongArray = null;
                if(wordCount > 8) {
                    optionalLongArray = reader.nextWords(wordCount-8);
                    
                }
                visitor.visitImageSparseSampleProjDrefExplicitLod(resultType, result, sampledImage, coordinate, dref, imageOperands, splitOperands, optionalLongArray);
            }
            break;
            
            case OpImageSparseFetch: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseFetch(resultType, result, image, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseGather: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long component = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseGather(resultType, result, sampledImage, coordinate, component, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseDrefGather: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long sampledImage = reader.nextWord();
                long coordinate = reader.nextWord();
                long dref = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 6) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 7) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseDrefGather(resultType, result, sampledImage, coordinate, dref, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpImageSparseTexelsResident: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long residentCode = reader.nextWord();
                visitor.visitImageSparseTexelsResident(resultType, result, residentCode);
            }
            break;
            
            case OpImageSparseRead: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long image = reader.nextWord();
                long coordinate = reader.nextWord();
                ImageOperands optionalImageOperands = new ImageOperands(0);if(wordCount > 5) {
                    optionalImageOperands = new ImageOperands(reader.nextWord());
                    
                }
                Map<Integer, long[]> splitOperands = Collections.emptyMap();
                if(wordCount > 6) {
                    int count_optionalImageOperands = optionalImageOperands.getOperandCount();
                    long[] values_optionalImageOperands = reader.nextWords(count_optionalImageOperands);
                    splitOperands = new HashMap<>();
                    optionalImageOperands.splitOperands(values_optionalImageOperands, splitOperands);
                }
                visitor.visitImageSparseRead(resultType, result, image, coordinate, optionalImageOperands, splitOperands);
            }
            break;
            
            case OpConvertFToU: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long floatValue = reader.nextWord();
                visitor.visitConvertFToU(resultType, result, floatValue);
            }
            break;
            
            case OpConvertFToS: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long floatValue = reader.nextWord();
                visitor.visitConvertFToS(resultType, result, floatValue);
            }
            break;
            
            case OpConvertSToF: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long signedValue = reader.nextWord();
                visitor.visitConvertSToF(resultType, result, signedValue);
            }
            break;
            
            case OpConvertUToF: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long unsignedValue = reader.nextWord();
                visitor.visitConvertUToF(resultType, result, unsignedValue);
            }
            break;
            
            case OpUConvert: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long unsignedValue = reader.nextWord();
                visitor.visitUConvert(resultType, result, unsignedValue);
            }
            break;
            
            case OpSConvert: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long signedValue = reader.nextWord();
                visitor.visitSConvert(resultType, result, signedValue);
            }
            break;
            
            case OpFConvert: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long floatValue = reader.nextWord();
                visitor.visitFConvert(resultType, result, floatValue);
            }
            break;
            
            case OpQuantizeToF16: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitQuantizeToF16(resultType, result, value);
            }
            break;
            
            case OpConvertPtrToU: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                visitor.visitConvertPtrToU(resultType, result, pointer);
            }
            break;
            
            case OpSatConvertSToU: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long signedValue = reader.nextWord();
                visitor.visitSatConvertSToU(resultType, result, signedValue);
            }
            break;
            
            case OpSatConvertUToS: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long unsignedValue = reader.nextWord();
                visitor.visitSatConvertUToS(resultType, result, unsignedValue);
            }
            break;
            
            case OpConvertUToPtr: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long integerValue = reader.nextWord();
                visitor.visitConvertUToPtr(resultType, result, integerValue);
            }
            break;
            
            case OpPtrCastToGeneric: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                visitor.visitPtrCastToGeneric(resultType, result, pointer);
            }
            break;
            
            case OpGenericCastToPtr: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                visitor.visitGenericCastToPtr(resultType, result, pointer);
            }
            break;
            
            case OpGenericCastToPtrExplicit: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                StorageClass storage = reader.nextEnumValue(StorageClass.values());
                visitor.visitGenericCastToPtrExplicit(resultType, result, pointer, storage);
            }
            break;
            
            case OpBitcast: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitBitcast(resultType, result, operand);
            }
            break;
            
            case OpVectorExtractDynamic: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                long index = reader.nextWord();
                visitor.visitVectorExtractDynamic(resultType, result, vector, index);
            }
            break;
            
            case OpVectorInsertDynamic: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                long component = reader.nextWord();
                long index = reader.nextWord();
                visitor.visitVectorInsertDynamic(resultType, result, vector, component, index);
            }
            break;
            
            case OpVectorShuffle: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector1 = reader.nextWord();
                long vector2 = reader.nextWord();
                long[] components = reader.nextWords(wordCount-5);
                visitor.visitVectorShuffle(resultType, result, vector1, vector2, components);
            }
            break;
            
            case OpCompositeConstruct: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] constituents = reader.nextWords(wordCount-3);
                visitor.visitCompositeConstruct(resultType, result, constituents);
            }
            break;
            
            case OpCompositeExtract: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long composite = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-4);
                visitor.visitCompositeExtract(resultType, result, composite, indexes);
            }
            break;
            
            case OpCompositeInsert: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long object = reader.nextWord();
                long composite = reader.nextWord();
                long[] indexes = reader.nextWords(wordCount-5);
                visitor.visitCompositeInsert(resultType, result, object, composite, indexes);
            }
            break;
            
            case OpCopyObject: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitCopyObject(resultType, result, operand);
            }
            break;
            
            case OpTranspose: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long matrix = reader.nextWord();
                visitor.visitTranspose(resultType, result, matrix);
            }
            break;
            
            case OpSNegate: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitSNegate(resultType, result, operand);
            }
            break;
            
            case OpFNegate: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitFNegate(resultType, result, operand);
            }
            break;
            
            case OpIAdd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitIAdd(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFAdd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFAdd(resultType, result, operand1, operand2);
            }
            break;
            
            case OpISub: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitISub(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFSub: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFSub(resultType, result, operand1, operand2);
            }
            break;
            
            case OpIMul: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitIMul(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFMul: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFMul(resultType, result, operand1, operand2);
            }
            break;
            
            case OpUDiv: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitUDiv(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSDiv: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSDiv(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFDiv: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFDiv(resultType, result, operand1, operand2);
            }
            break;
            
            case OpUMod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitUMod(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSRem: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSRem(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSMod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSMod(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFRem: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFRem(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFMod: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFMod(resultType, result, operand1, operand2);
            }
            break;
            
            case OpVectorTimesScalar: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                long scalar = reader.nextWord();
                visitor.visitVectorTimesScalar(resultType, result, vector, scalar);
            }
            break;
            
            case OpMatrixTimesScalar: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long matrix = reader.nextWord();
                long scalar = reader.nextWord();
                visitor.visitMatrixTimesScalar(resultType, result, matrix, scalar);
            }
            break;
            
            case OpVectorTimesMatrix: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                long matrix = reader.nextWord();
                visitor.visitVectorTimesMatrix(resultType, result, vector, matrix);
            }
            break;
            
            case OpMatrixTimesVector: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long matrix = reader.nextWord();
                long vector = reader.nextWord();
                visitor.visitMatrixTimesVector(resultType, result, matrix, vector);
            }
            break;
            
            case OpMatrixTimesMatrix: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long leftMatrix = reader.nextWord();
                long rightMatrix = reader.nextWord();
                visitor.visitMatrixTimesMatrix(resultType, result, leftMatrix, rightMatrix);
            }
            break;
            
            case OpOuterProduct: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector1 = reader.nextWord();
                long vector2 = reader.nextWord();
                visitor.visitOuterProduct(resultType, result, vector1, vector2);
            }
            break;
            
            case OpDot: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector1 = reader.nextWord();
                long vector2 = reader.nextWord();
                visitor.visitDot(resultType, result, vector1, vector2);
            }
            break;
            
            case OpIAddCarry: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitIAddCarry(resultType, result, operand1, operand2);
            }
            break;
            
            case OpISubBorrow: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitISubBorrow(resultType, result, operand1, operand2);
            }
            break;
            
            case OpUMulExtended: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitUMulExtended(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSMulExtended: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSMulExtended(resultType, result, operand1, operand2);
            }
            break;
            
            case OpShiftRightLogical: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long shift = reader.nextWord();
                visitor.visitShiftRightLogical(resultType, result, base, shift);
            }
            break;
            
            case OpShiftRightArithmetic: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long shift = reader.nextWord();
                visitor.visitShiftRightArithmetic(resultType, result, base, shift);
            }
            break;
            
            case OpShiftLeftLogical: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long shift = reader.nextWord();
                visitor.visitShiftLeftLogical(resultType, result, base, shift);
            }
            break;
            
            case OpBitwiseOr: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitBitwiseOr(resultType, result, operand1, operand2);
            }
            break;
            
            case OpBitwiseXor: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitBitwiseXor(resultType, result, operand1, operand2);
            }
            break;
            
            case OpBitwiseAnd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitBitwiseAnd(resultType, result, operand1, operand2);
            }
            break;
            
            case OpNot: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitNot(resultType, result, operand);
            }
            break;
            
            case OpBitFieldInsert: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long insert = reader.nextWord();
                long offset = reader.nextWord();
                long count = reader.nextWord();
                visitor.visitBitFieldInsert(resultType, result, base, insert, offset, count);
            }
            break;
            
            case OpBitFieldSExtract: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long offset = reader.nextWord();
                long count = reader.nextWord();
                visitor.visitBitFieldSExtract(resultType, result, base, offset, count);
            }
            break;
            
            case OpBitFieldUExtract: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                long offset = reader.nextWord();
                long count = reader.nextWord();
                visitor.visitBitFieldUExtract(resultType, result, base, offset, count);
            }
            break;
            
            case OpBitReverse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                visitor.visitBitReverse(resultType, result, base);
            }
            break;
            
            case OpBitCount: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long base = reader.nextWord();
                visitor.visitBitCount(resultType, result, base);
            }
            break;
            
            case OpAny: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                visitor.visitAny(resultType, result, vector);
            }
            break;
            
            case OpAll: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long vector = reader.nextWord();
                visitor.visitAll(resultType, result, vector);
            }
            break;
            
            case OpIsNan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitIsNan(resultType, result, x);
            }
            break;
            
            case OpIsInf: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitIsInf(resultType, result, x);
            }
            break;
            
            case OpIsFinite: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitIsFinite(resultType, result, x);
            }
            break;
            
            case OpIsNormal: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitIsNormal(resultType, result, x);
            }
            break;
            
            case OpSignBitSet: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitSignBitSet(resultType, result, x);
            }
            break;
            
            case OpLessOrGreater: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                long y = reader.nextWord();
                visitor.visitLessOrGreater(resultType, result, x, y);
            }
            break;
            
            case OpOrdered: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                long y = reader.nextWord();
                visitor.visitOrdered(resultType, result, x, y);
            }
            break;
            
            case OpUnordered: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long x = reader.nextWord();
                long y = reader.nextWord();
                visitor.visitUnordered(resultType, result, x, y);
            }
            break;
            
            case OpLogicalEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitLogicalEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpLogicalNotEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitLogicalNotEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpLogicalOr: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitLogicalOr(resultType, result, operand1, operand2);
            }
            break;
            
            case OpLogicalAnd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitLogicalAnd(resultType, result, operand1, operand2);
            }
            break;
            
            case OpLogicalNot: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand = reader.nextWord();
                visitor.visitLogicalNot(resultType, result, operand);
            }
            break;
            
            case OpSelect: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long condition = reader.nextWord();
                long object1 = reader.nextWord();
                long object2 = reader.nextWord();
                visitor.visitSelect(resultType, result, condition, object1, object2);
            }
            break;
            
            case OpIEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitIEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpINotEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitINotEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpUGreaterThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitUGreaterThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSGreaterThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSGreaterThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpUGreaterThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitUGreaterThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSGreaterThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSGreaterThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpULessThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitULessThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSLessThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSLessThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpULessThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitULessThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpSLessThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitSLessThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdNotEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdNotEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordNotEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordNotEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdLessThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdLessThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordLessThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordLessThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdGreaterThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdGreaterThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordGreaterThan: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordGreaterThan(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdLessThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdLessThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordLessThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordLessThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFOrdGreaterThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFOrdGreaterThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpFUnordGreaterThanEqual: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long operand1 = reader.nextWord();
                long operand2 = reader.nextWord();
                visitor.visitFUnordGreaterThanEqual(resultType, result, operand1, operand2);
            }
            break;
            
            case OpDPdx: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdx(resultType, result, p);
            }
            break;
            
            case OpDPdy: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdy(resultType, result, p);
            }
            break;
            
            case OpFwidth: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitFwidth(resultType, result, p);
            }
            break;
            
            case OpDPdxFine: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdxFine(resultType, result, p);
            }
            break;
            
            case OpDPdyFine: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdyFine(resultType, result, p);
            }
            break;
            
            case OpFwidthFine: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitFwidthFine(resultType, result, p);
            }
            break;
            
            case OpDPdxCoarse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdxCoarse(resultType, result, p);
            }
            break;
            
            case OpDPdyCoarse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitDPdyCoarse(resultType, result, p);
            }
            break;
            
            case OpFwidthCoarse: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long p = reader.nextWord();
                visitor.visitFwidthCoarse(resultType, result, p);
            }
            break;
            
            case OpPhi: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long[] variables = reader.nextWords(wordCount-3);
                visitor.visitPhi(resultType, result, variables);
            }
            break;
            
            case OpLoopMerge: {
                long mergeBlock = reader.nextWord();
                long continueTarget = reader.nextWord();
                long loopControl = reader.nextWord();
                long[] parameters = reader.nextWords(wordCount-4);
                visitor.visitLoopMerge(mergeBlock, continueTarget, loopControl, parameters);
            }
            break;
            
            case OpSelectionMerge: {
                long mergeBlock = reader.nextWord();
                long selectionControl = reader.nextWord();
                visitor.visitSelectionMerge(mergeBlock, selectionControl);
            }
            break;
            
            case OpLabel: {
                long result = reader.nextWord();
                visitor.visitLabel(result);
            }
            break;
            
            case OpBranch: {
                long targetLabel = reader.nextWord();
                visitor.visitBranch(targetLabel);
            }
            break;
            
            case OpBranchConditional: {
                long condition = reader.nextWord();
                long trueLabel = reader.nextWord();
                long falseLabel = reader.nextWord();
                long[] weights = reader.nextWords(wordCount-4);
                visitor.visitBranchConditional(condition, trueLabel, falseLabel, weights);
            }
            break;
            
            case OpSwitch: {
                long selector = reader.nextWord();
                long defaultValue = reader.nextWord();
                long[] target = reader.nextWords(wordCount-3);
                visitor.visitSwitch(selector, defaultValue, target);
            }
            break;
            
            case OpKill: {
                visitor.visitKill();
            }
            break;
            
            case OpReturn: {
                visitor.visitReturn();
            }
            break;
            
            case OpReturnValue: {
                long value = reader.nextWord();
                visitor.visitReturnValue(value);
            }
            break;
            
            case OpUnreachable: {
                visitor.visitUnreachable();
            }
            break;
            
            case OpLifetimeStart: {
                long pointer = reader.nextWord();
                long size = reader.nextWord();
                visitor.visitLifetimeStart(pointer, size);
            }
            break;
            
            case OpLifetimeStop: {
                long pointer = reader.nextWord();
                long size = reader.nextWord();
                visitor.visitLifetimeStop(pointer, size);
            }
            break;
            
            case OpAtomicLoad: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitAtomicLoad(resultType, result, pointer, scopeScope, memorySemanticsSemantics);
            }
            break;
            
            case OpAtomicStore: {
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicStore(pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicExchange: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicExchange(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicCompareExchange: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsEqual = reader.nextWord();
                long memorySemanticsUnequal = reader.nextWord();
                long value = reader.nextWord();
                long comparator = reader.nextWord();
                visitor.visitAtomicCompareExchange(resultType, result, pointer, scopeScope, memorySemanticsEqual, memorySemanticsUnequal, value, comparator);
            }
            break;
            
            case OpAtomicCompareExchangeWeak: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsEqual = reader.nextWord();
                long memorySemanticsUnequal = reader.nextWord();
                long value = reader.nextWord();
                long comparator = reader.nextWord();
                visitor.visitAtomicCompareExchangeWeak(resultType, result, pointer, scopeScope, memorySemanticsEqual, memorySemanticsUnequal, value, comparator);
            }
            break;
            
            case OpAtomicIIncrement: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitAtomicIIncrement(resultType, result, pointer, scopeScope, memorySemanticsSemantics);
            }
            break;
            
            case OpAtomicIDecrement: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitAtomicIDecrement(resultType, result, pointer, scopeScope, memorySemanticsSemantics);
            }
            break;
            
            case OpAtomicIAdd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicIAdd(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicISub: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicISub(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicSMin: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicSMin(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicUMin: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicUMin(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicSMax: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicSMax(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicUMax: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicUMax(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicAnd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicAnd(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicOr: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicOr(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicXor: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitAtomicXor(resultType, result, pointer, scopeScope, memorySemanticsSemantics, value);
            }
            break;
            
            case OpAtomicFlagTestAndSet: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitAtomicFlagTestAndSet(resultType, result, pointer, scopeScope, memorySemanticsSemantics);
            }
            break;
            
            case OpAtomicFlagClear: {
                long pointer = reader.nextWord();
                long scopeScope = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitAtomicFlagClear(pointer, scopeScope, memorySemanticsSemantics);
            }
            break;
            
            case OpEmitVertex: {
                visitor.visitEmitVertex();
            }
            break;
            
            case OpEndPrimitive: {
                visitor.visitEndPrimitive();
            }
            break;
            
            case OpEmitStreamVertex: {
                long stream = reader.nextWord();
                visitor.visitEmitStreamVertex(stream);
            }
            break;
            
            case OpEndStreamPrimitive: {
                long stream = reader.nextWord();
                visitor.visitEndStreamPrimitive(stream);
            }
            break;
            
            case OpControlBarrier: {
                long scopeExecution = reader.nextWord();
                long scopeMemory = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitControlBarrier(scopeExecution, scopeMemory, memorySemanticsSemantics);
            }
            break;
            
            case OpMemoryBarrier: {
                long scopeMemory = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitMemoryBarrier(scopeMemory, memorySemanticsSemantics);
            }
            break;
            
            case OpNamedBarrierInitialize: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long subgroupCount = reader.nextWord();
                visitor.visitNamedBarrierInitialize(resultType, result, subgroupCount);
            }
            break;
            
            case OpMemoryNamedBarrier: {
                long namedBarrier = reader.nextWord();
                long scopeMemory = reader.nextWord();
                long memorySemanticsSemantics = reader.nextWord();
                visitor.visitMemoryNamedBarrier(namedBarrier, scopeMemory, memorySemanticsSemantics);
            }
            break;
            
            case OpGroupAsyncCopy: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long destination = reader.nextWord();
                long source = reader.nextWord();
                long numElements = reader.nextWord();
                long stride = reader.nextWord();
                long event = reader.nextWord();
                visitor.visitGroupAsyncCopy(resultType, result, scopeExecution, destination, source, numElements, stride, event);
            }
            break;
            
            case OpGroupWaitEvents: {
                long scopeExecution = reader.nextWord();
                long numEvents = reader.nextWord();
                long eventsList = reader.nextWord();
                visitor.visitGroupWaitEvents(scopeExecution, numEvents, eventsList);
            }
            break;
            
            case OpGroupAll: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long predicate = reader.nextWord();
                visitor.visitGroupAll(resultType, result, scopeExecution, predicate);
            }
            break;
            
            case OpGroupAny: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long predicate = reader.nextWord();
                visitor.visitGroupAny(resultType, result, scopeExecution, predicate);
            }
            break;
            
            case OpGroupBroadcast: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long value = reader.nextWord();
                long localId = reader.nextWord();
                visitor.visitGroupBroadcast(resultType, result, scopeExecution, value, localId);
            }
            break;
            
            case OpGroupIAdd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupIAdd(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupFAdd: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupFAdd(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupFMin: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupFMin(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupUMin: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupUMin(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupSMin: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupSMin(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupFMax: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupFMax(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupUMax: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupUMax(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpGroupSMax: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long operation = reader.nextWord();
                long x = reader.nextWord();
                visitor.visitGroupSMax(resultType, result, scopeExecution, operation, x);
            }
            break;
            
            case OpSubgroupBallotKHR: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long predicate = reader.nextWord();
                visitor.visitSubgroupBallotKHR(resultType, result, predicate);
            }
            break;
            
            case OpSubgroupFirstInvocationKHR: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitSubgroupFirstInvocationKHR(resultType, result, value);
            }
            break;
            
            case OpSubgroupReadInvocationKHR: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long value = reader.nextWord();
                long index = reader.nextWord();
                visitor.visitSubgroupReadInvocationKHR(resultType, result, value, index);
            }
            break;
            
            case OpEnqueueMarker: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long queue = reader.nextWord();
                long numEvents = reader.nextWord();
                long waitEvents = reader.nextWord();
                long retEvent = reader.nextWord();
                visitor.visitEnqueueMarker(resultType, result, queue, numEvents, waitEvents, retEvent);
            }
            break;
            
            case OpEnqueueKernel: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long queue = reader.nextWord();
                long flags = reader.nextWord();
                long nDRange = reader.nextWord();
                long numEvents = reader.nextWord();
                long waitEvents = reader.nextWord();
                long retEvent = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                long[] locals = reader.nextWords(wordCount-13);
                visitor.visitEnqueueKernel(resultType, result, queue, flags, nDRange, numEvents, waitEvents, retEvent, invoke, param, paramSize, paramAlign, locals);
            }
            break;
            
            case OpGetKernelNDrangeSubGroupCount: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long nDRange = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelNDrangeSubGroupCount(resultType, result, nDRange, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpGetKernelNDrangeMaxSubGroupSize: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long nDRange = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelNDrangeMaxSubGroupSize(resultType, result, nDRange, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpGetKernelWorkGroupSize: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelWorkGroupSize(resultType, result, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpGetKernelPreferredWorkGroupSizeMultiple: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelPreferredWorkGroupSizeMultiple(resultType, result, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpRetainEvent: {
                long event = reader.nextWord();
                visitor.visitRetainEvent(event);
            }
            break;
            
            case OpReleaseEvent: {
                long event = reader.nextWord();
                visitor.visitReleaseEvent(event);
            }
            break;
            
            case OpCreateUserEvent: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitCreateUserEvent(resultType, result);
            }
            break;
            
            case OpIsValidEvent: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long event = reader.nextWord();
                visitor.visitIsValidEvent(resultType, result, event);
            }
            break;
            
            case OpSetUserEventStatus: {
                long event = reader.nextWord();
                long status = reader.nextWord();
                visitor.visitSetUserEventStatus(event, status);
            }
            break;
            
            case OpCaptureEventProfilingInfo: {
                long event = reader.nextWord();
                long profilingInfo = reader.nextWord();
                long value = reader.nextWord();
                visitor.visitCaptureEventProfilingInfo(event, profilingInfo, value);
            }
            break;
            
            case OpGetDefaultQueue: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                visitor.visitGetDefaultQueue(resultType, result);
            }
            break;
            
            case OpBuildNDRange: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long globalWorkSize = reader.nextWord();
                long localWorkSize = reader.nextWord();
                long globalWorkOffset = reader.nextWord();
                visitor.visitBuildNDRange(resultType, result, globalWorkSize, localWorkSize, globalWorkOffset);
            }
            break;
            
            case OpGetKernelLocalSizeForSubgroupCount: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long subgroupCount = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelLocalSizeForSubgroupCount(resultType, result, subgroupCount, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpGetKernelMaxNumSubgroups: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long invoke = reader.nextWord();
                long param = reader.nextWord();
                long paramSize = reader.nextWord();
                long paramAlign = reader.nextWord();
                visitor.visitGetKernelMaxNumSubgroups(resultType, result, invoke, param, paramSize, paramAlign);
            }
            break;
            
            case OpReadPipe: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long pointer = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitReadPipe(resultType, result, pipe, pointer, packetSize, packetAlignment);
            }
            break;
            
            case OpWritePipe: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long pointer = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitWritePipe(resultType, result, pipe, pointer, packetSize, packetAlignment);
            }
            break;
            
            case OpReservedReadPipe: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long index = reader.nextWord();
                long pointer = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitReservedReadPipe(resultType, result, pipe, reserveId, index, pointer, packetSize, packetAlignment);
            }
            break;
            
            case OpReservedWritePipe: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long index = reader.nextWord();
                long pointer = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitReservedWritePipe(resultType, result, pipe, reserveId, index, pointer, packetSize, packetAlignment);
            }
            break;
            
            case OpReserveReadPipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long numPackets = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitReserveReadPipePackets(resultType, result, pipe, numPackets, packetSize, packetAlignment);
            }
            break;
            
            case OpReserveWritePipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long numPackets = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitReserveWritePipePackets(resultType, result, pipe, numPackets, packetSize, packetAlignment);
            }
            break;
            
            case OpCommitReadPipe: {
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitCommitReadPipe(pipe, reserveId, packetSize, packetAlignment);
            }
            break;
            
            case OpCommitWritePipe: {
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitCommitWritePipe(pipe, reserveId, packetSize, packetAlignment);
            }
            break;
            
            case OpIsValidReserveId: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long reserveId = reader.nextWord();
                visitor.visitIsValidReserveId(resultType, result, reserveId);
            }
            break;
            
            case OpGetNumPipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGetNumPipePackets(resultType, result, pipe, packetSize, packetAlignment);
            }
            break;
            
            case OpGetMaxPipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipe = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGetMaxPipePackets(resultType, result, pipe, packetSize, packetAlignment);
            }
            break;
            
            case OpGroupReserveReadPipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long pipe = reader.nextWord();
                long numPackets = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGroupReserveReadPipePackets(resultType, result, scopeExecution, pipe, numPackets, packetSize, packetAlignment);
            }
            break;
            
            case OpGroupReserveWritePipePackets: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long scopeExecution = reader.nextWord();
                long pipe = reader.nextWord();
                long numPackets = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGroupReserveWritePipePackets(resultType, result, scopeExecution, pipe, numPackets, packetSize, packetAlignment);
            }
            break;
            
            case OpGroupCommitReadPipe: {
                long scopeExecution = reader.nextWord();
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGroupCommitReadPipe(scopeExecution, pipe, reserveId, packetSize, packetAlignment);
            }
            break;
            
            case OpGroupCommitWritePipe: {
                long scopeExecution = reader.nextWord();
                long pipe = reader.nextWord();
                long reserveId = reader.nextWord();
                long packetSize = reader.nextWord();
                long packetAlignment = reader.nextWord();
                visitor.visitGroupCommitWritePipe(scopeExecution, pipe, reserveId, packetSize, packetAlignment);
            }
            break;
            
            case OpConstantPipeStorage: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long size = reader.nextWord();
                long alignment = reader.nextWord();
                long capacity = reader.nextWord();
                visitor.visitConstantPipeStorage(resultType, result, size, alignment, capacity);
            }
            break;
            
            case OpCreatePipeFromPipeStorage: {
                long resultType = reader.nextWord();
                long result = reader.nextWord();
                long pipeStorage = reader.nextWord();
                visitor.visitCreatePipeFromPipeStorage(resultType, result, pipeStorage);
            }
            break;
            
            
            case OpDecorate: {
                            long target = reader.nextWord();
                            Decoration decoration = reader.nextEnumValue(Decoration.values());
                            reader.visitDecoration(visitor, decoration, target, wordCount);
                    }
                    break;
            
            case OpMemberDecorate: {
                            long structureType = reader.nextWord();
                            long member = reader.nextWord();
                            Decoration decoration = reader.nextEnumValue(Decoration.values());
                            reader.visitMemberDecoration(visitor, decoration, structureType, member, wordCount);
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
            
            case OpExecutionMode: {
                            long entryPoint = reader.nextWord();
                            ExecutionMode.Type type = reader.nextEnumValue(ExecutionMode.Type.values());
                            ExecutionMode mode = reader.readMode(type);
                            visitor.visitExecutionMode(entryPoint, mode);
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
            default:
                throw new IllegalStateException("Unhandled: " + Opcodes.getName(opcodeID) + " " + opcodeID + " / " + wordCount+" at "+reader.position);
        }
    }
}

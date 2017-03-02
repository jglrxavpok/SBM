package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;

import java.util.Map;

public class AbstractCodeVisitor implements CodeVisitor {
    @Override
    public void visitConstantTrue(long type, long resultID) {

    }

    @Override
    public void visitConstantFalse(long type, long resultID) {

    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {

    }

    @Override
    public void visitConstantComposite(long resultType, long resultID, long[] constituents) {

    }

    @Override
    public void visitConstantSampler(long resultType, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter) {

    }

    @Override
    public void visitConstantNull(long resultType, long resultID) {

    }

    @Override
    public void visitSpecConstantTrue(long resultType, long resultID) {

    }

    @Override
    public void visitSpecConstantFalse(long resultType, long resultID) {

    }

    @Override
    public void visitSpecConstant(long resultType, long resultID, long[] value) {

    }

    @Override
    public void visitSpecConstantComposite(long resultType, long resultID, long[] constituents) {

    }

    @Override
    public void visitSpecConstantOp(long resultType, long resultID, long opcode, long[] operands) {

    }

    @Override
    public void visitSampledImage(long resultType, long result, long image, long sampler) {

    }

    @Override
    public void visitVoidType(long resultID) {

    }

    @Override
    public void visitBoolType(long resultID) {

    }

    @Override
    public void visitFloatType(long resultID, long width) {

    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {

    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {

    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {

    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {

    }

    @Override
    public void visitSamplerType(long resultID) {

    }

    @Override
    public void visitSampledImageType(long resultID, long imageType) {

    }

    @Override
    public void visitArrayType(long resultID, long elementType, long length) {

    }

    @Override
    public void visitRuntimeArrayType(long resultID, long elementType) {

    }

    @Override
    public void visitStructType(long resultID, long[] memberTypes) {

    }

    @Override
    public void visitOpaqueType(long resultID, String name) {

    }

    @Override
    public void visitPointerType(long resultID, StorageClass storageClass, long type) {

    }

    @Override
    public void visitFunctionType(long resultID, long returnType, long[] parameterTypes) {

    }

    @Override
    public void visitEventType(long resultID) {

    }

    @Override
    public void visitDeviceEventType(long resultID) {

    }

    @Override
    public void visitReserveIdType(long result) {

    }

    @Override
    public void visitQueueType(long resultID) {

    }

    @Override
    public void visitPipeType(long resultID, AccessQualifier accessQualifier) {

    }

    @Override
    public void visitForwardPointerType(long pointerType, StorageClass storageClass) {

    }

    @Override
    public void visitNamedBarrierType(long resultID) {

    }

    @Override
    public void visitPipeStorageType(long resultID) {

    }

    @Override
    public void visitIntDecoration(Decoration decoration, long target, long value) {

    }

    @Override
    public void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute) {

    }

    @Override
    public void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode) {

    }

    @Override
    public void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode) {

    }

    @Override
    public void visitLinkageAttributesDecoration(long target, String name, LinkageType type) {

    }

    @Override
    public void visitDecoration(long target, Decoration decoration) {

    }

    @Override
    public void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value) {

    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute) {

    }

    @Override
    public void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode) {

    }

    @Override
    public void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode) {

    }

    @Override
    public void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType) {

    }

    @Override
    public void visitMemberDecoration(long structureType, long member, Decoration decoration) {

    }

    @Override
    public void visitDecorationGroup(long resultID) {

    }

    @Override
    public void visitGroupDecoration(long decorationGroup, long[] targets) {

    }

    @Override
    public void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets) {

    }

    @Override
    public void visitCopyMemory(long targetID, long sourceID, MemoryAccess access) {

    }

    @Override
    public void visitCopyMemorySized(long targetID, long sourceID, long size, MemoryAccess access) {

    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {

    }

    @Override
    public void visitStore(long pointer, long object, MemoryAccess memoryAccess) {

    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {

    }

    @Override
    public void visitAccessChain(long resultType, long resultID, long base, long[] indexes) {

    }

    @Override
    public void visitInBoundsAccessChain(long resultType, long result, long base, long[] indexes) {

    }

    @Override
    public void visitPtrAccessChain(long resultType, long result, long base, long element, long[] indexes) {

    }

    @Override
    public void visitArrayLength(long resultType, long result, long structure, long member) {

    }

    @Override
    public void visitGenericPtrMemSemantics(long resultType, long result, long pointer) {

    }

    @Override
    public void visitInBoundsPtrAccessChain(long resultType, long result, long base, long element, long[] indexes) {

    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {

    }

    @Override
    public void visitImageTexelPointer(long resultType, long result, long image, long coordinate, long sample) {

    }


    @Override
    public void visitFunction(long resultType, long resultID, FunctionControl control, long funcType) {

    }

    @Override
    public void visitFunctionEnd() {

    }

    @Override
    public void visitFunctionCall(long resultType, long resultID, long functionID, long[] arguments) {

    }

    @Override
    public void visitFunctionParameter(long resultType, long resultID) {

    }

    @Override
    public void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode) {

    }

    @Override
    public void visitSourceContinued(String source) {

    }

    @Override
    public void visitSourceExtension(String source) {

    }

    @Override
    public void visitLine(long filenameID, long line, long column) {

    }

    @Override
    public void visitName(long target, String name) {

    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {

    }

    @Override
    public void visitString(long resultID, String value) {

    }

    @Override
    public void visitCapability(Capability cap) {

    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {

    }

    @Override
    public void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands) {

    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {

    }

    @Override
    public void visitLabel(long resultID) {

    }

    @Override
    public void visitBranch(long targetLabel) {

    }

    @Override
    public void visitBranchConditional(long condition, long trueLabel, long falseLabel, long[] weights) {

    }

    @Override
    public void visitSwitch(long selector, long defaultValue, long[] target) {

    }

    @Override
    public void visitExtension(String extension) {

    }

    @Override
    public void visitReturn() {

    }

    public void visitEnd() {

    }

    public void reset() {

    }

    @Override
    public void visitNop() {

    }

    @Override
    public void visitUndef(long resultType, long resultID) {

    }

    @Override
    public void visitSizeOf(long resultType, long result, long pointer) {

    }

    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {

    }

    @Override
    public void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageWrite(long image, long coordinate, long texel, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImage(long resultType, long result, long sampledImage) {

    }

    @Override
    public void visitImageQueryFormat(long resultType, long result, long image) {

    }

    @Override
    public void visitImageQueryOrder(long resultType, long result, long image) {

    }

    @Override
    public void visitImageQuerySizeLod(long resultType, long result, long image, long levelOfDetail) {

    }

    @Override
    public void visitImageQuerySize(long resultType, long result, long image) {

    }

    @Override
    public void visitImageQueryLod(long resultType, long result, long sampledImage, long coordinate) {

    }

    @Override
    public void visitImageQueryLevels(long resultType, long result, long image) {

    }

    @Override
    public void visitImageQuerySamples(long resultType, long result, long image) {

    }

    @Override
    public void visitImageSparseSampleImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseSampleExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSparseSampleDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseSampleDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSparseSampleProjImplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseSampleProjExplicitLod(long resultType, long result, long sampledImage, long coordinate, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSparseSampleProjDrefImplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseSampleProjDrefExplicitLod(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands imageOperands, Map<Integer, long[]> splitOperands, long[] optionalLongArray) {

    }

    @Override
    public void visitImageSparseFetch(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseGather(long resultType, long result, long sampledImage, long coordinate, long component, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseDrefGather(long resultType, long result, long sampledImage, long coordinate, long dref, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitImageSparseTexelsResident(long resultType, long result, long residentCode) {

    }

    @Override
    public void visitImageSparseRead(long resultType, long result, long image, long coordinate, ImageOperands optionalImageOperands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitConvertFToU(long resultType, long result, long floatValue) {

    }

    @Override
    public void visitConvertFToS(long resultType, long result, long floatValue) {

    }

    @Override
    public void visitConvertSToF(long resultType, long result, long signedValue) {

    }

    @Override
    public void visitConvertUToF(long resultType, long result, long unsignedValue) {

    }

    @Override
    public void visitUConvert(long resultType, long result, long unsignedValue) {

    }

    @Override
    public void visitSConvert(long resultType, long result, long signedValue) {

    }

    @Override
    public void visitFConvert(long resultType, long result, long floatValue) {

    }

    @Override
    public void visitQuantizeToF16(long resultType, long result, long value) {

    }

    @Override
    public void visitConvertPtrToU(long resultType, long result, long pointer) {

    }

    @Override
    public void visitSatConvertSToU(long resultType, long result, long signedValue) {

    }

    @Override
    public void visitSatConvertUToS(long resultType, long result, long unsignedValue) {

    }

    @Override
    public void visitConvertUToPtr(long resultType, long result, long integerValue) {

    }

    @Override
    public void visitPtrCastToGeneric(long resultType, long result, long pointer) {

    }

    @Override
    public void visitGenericCastToPtr(long resultType, long result, long pointer) {

    }

    @Override
    public void visitGenericCastToPtrExplicit(long resultType, long result, long pointer, StorageClass storage) {

    }

    @Override
    public void visitBitcast(long resultType, long result, long operand) {

    }

    @Override
    public void visitVectorExtractDynamic(long resultType, long result, long vector, long index) {

    }

    @Override
    public void visitVectorInsertDynamic(long resultType, long result, long vector, long component, long index) {

    }

    @Override
    public void visitVectorShuffle(long resultType, long result, long vector1, long vector2, long[] components) {

    }

    @Override
    public void visitKill() {

    }

    @Override
    public void visitReturnValue(long valueID) {

    }

    @Override
    public void visitUnreachable() {

    }

    @Override
    public void visitLifetimeStart(long pointer, long size) {

    }

    @Override
    public void visitLifetimeStop(long pointer, long size) {

    }

    @Override
    public void visitAtomicLoad(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {

    }

    @Override
    public void visitAtomicStore(long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicCompareExchange(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator) {

    }

    @Override
    public void visitAtomicCompareExchangeWeak(long resultType, long result, long pointer, long scopeScope, long memorySemanticsEqual, long memorySemanticsUnequal, long value, long comparator) {

    }

    @Override
    public void visitAtomicIIncrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {

    }

    @Override
    public void visitAtomicIDecrement(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {

    }

    @Override
    public void visitAtomicIAdd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicISub(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicSMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicUMin(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicSMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicUMax(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicAnd(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicOr(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicXor(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics, long value) {

    }

    @Override
    public void visitAtomicFlagTestAndSet(long resultType, long result, long pointer, long scopeScope, long memorySemanticsSemantics) {

    }

    @Override
    public void visitAtomicFlagClear(long pointer, long scopeScope, long memorySemanticsSemantics) {

    }

    @Override
    public void visitEmitVertex() {

    }

    @Override
    public void visitEndPrimitive() {

    }

    @Override
    public void visitEmitStreamVertex(long stream) {

    }

    @Override
    public void visitEndStreamPrimitive(long stream) {

    }

    @Override
    public void visitControlBarrier(long scopeExecution, long scopeMemory, long memorySemanticsSemantics) {

    }

    @Override
    public void visitMemoryBarrier(long scopeMemory, long memorySemanticsSemantics) {

    }

    @Override
    public void visitNamedBarrierInitialize(long resultType, long result, long subgroupCount) {

    }

    @Override
    public void visitMemoryNamedBarrier(long namedBarrier, long scopeMemory, long memorySemanticsSemantics) {

    }

    @Override
    public void visitGroupAsyncCopy(long resultType, long result, long scopeExecution, long destination, long source, long numElements, long stride, long event) {

    }

    @Override
    public void visitGroupWaitEvents(long scopeExecution, long numEvents, long eventsList) {

    }

    @Override
    public void visitGroupAll(long resultType, long result, long scopeExecution, long predicate) {

    }

    @Override
    public void visitGroupAny(long resultType, long result, long scopeExecution, long predicate) {

    }

    @Override
    public void visitGroupBroadcast(long resultType, long result, long scopeExecution, long value, long localId) {

    }

    @Override
    public void visitGroupIAdd(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupFAdd(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupFMin(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupUMin(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupSMin(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupFMax(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupUMax(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitGroupSMax(long resultType, long result, long scopeExecution, long operation, long x) {

    }

    @Override
    public void visitSubgroupBallotKHR(long resultType, long result, long predicate) {

    }

    @Override
    public void visitSubgroupFirstInvocationKHR(long resultType, long result, long value) {

    }

    @Override
    public void visitSubgroupReadInvocationKHR(long resultType, long result, long value, long index) {

    }

    @Override
    public void visitEnqueueMarker(long resultType, long result, long queue, long numEvents, long waitEvents, long retEvent) {

    }

    @Override
    public void visitEnqueueKernel(long resultType, long result, long queue, long flags, long nDRange, long numEvents, long waitEvents, long retEvent, long invoke, long param, long paramSize, long paramAlign, long[] locals) {

    }

    @Override
    public void visitGetKernelNDrangeSubGroupCount(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitGetKernelNDrangeMaxSubGroupSize(long resultType, long result, long nDRange, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitGetKernelWorkGroupSize(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitGetKernelPreferredWorkGroupSizeMultiple(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitRetainEvent(long event) {

    }

    @Override
    public void visitReleaseEvent(long event) {

    }

    @Override
    public void visitCreateUserEvent(long resultType, long result) {

    }

    @Override
    public void visitIsValidEvent(long resultType, long result, long event) {

    }

    @Override
    public void visitSetUserEventStatus(long event, long status) {

    }

    @Override
    public void visitCaptureEventProfilingInfo(long event, long profilingInfo, long value) {

    }

    @Override
    public void visitGetDefaultQueue(long resultType, long result) {

    }

    @Override
    public void visitBuildNDRange(long resultType, long result, long globalWorkSize, long localWorkSize, long globalWorkOffset) {

    }

    @Override
    public void visitGetKernelLocalSizeForSubgroupCount(long resultType, long result, long subgroupCount, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitGetKernelMaxNumSubgroups(long resultType, long result, long invoke, long param, long paramSize, long paramAlign) {

    }

    @Override
    public void visitReadPipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitWritePipe(long resultType, long result, long pipe, long pointer, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitReservedReadPipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitReservedWritePipe(long resultType, long result, long pipe, long reserveId, long index, long pointer, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitReserveReadPipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitReserveWritePipePackets(long resultType, long result, long pipe, long numPackets, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitCommitReadPipe(long pipe, long reserveId, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitCommitWritePipe(long pipe, long reserveId, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitIsValidReserveId(long resultType, long result, long reserveId) {

    }

    @Override
    public void visitGetNumPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitGetMaxPipePackets(long resultType, long result, long pipe, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitGroupReserveReadPipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitGroupReserveWritePipePackets(long resultType, long result, long scopeExecution, long pipe, long numPackets, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitGroupCommitReadPipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitGroupCommitWritePipe(long scopeExecution, long pipe, long reserveId, long packetSize, long packetAlignment) {

    }

    @Override
    public void visitConstantPipeStorage(long resultType, long result, long size, long alignment, long capacity) {

    }

    @Override
    public void visitCreatePipeFromPipeStorage(long resultType, long result, long pipeStorage) {

    }

    @Override
    public void visitNoLine() {

    }

    @Override
    public void visitModuleProcessed(String process) {

    }

    @Override
    public void visitCompositeConstruct(long resultType, long resultID, long... constituents) {

    }

    @Override
    public void visitSNegate(long resultTypeID, long resultID, long operandID) {

    }

    @Override
    public void visitFNegate(long resultTypeID, long resultID, long operandID) {

    }

    @Override
    public void visitIAdd(long resultTypeID, long resultID, long leftID, long rightID) {
        
    }

    @Override
    public void visitFAdd(long resultTypeID, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitISub(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitFSub(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitIMul(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitFMul(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitUDiv(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitSDiv(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitFDiv(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitUMod(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitSRem(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitSMod(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitFRem(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitFMod(long resultType, long resultID, long leftID, long rightID) {

    }

    @Override
    public void visitVectorTimesScalar(long resultType, long result, long vector, long scalar) {

    }

    @Override
    public void visitMatrixTimesScalar(long resultType, long result, long matrix, long scalar) {

    }

    @Override
    public void visitVectorTimesMatrix(long resultType, long result, long vector, long matrix) {

    }

    @Override
    public void visitMatrixTimesVector(long resultType, long result, long matrix, long vector) {

    }

    @Override
    public void visitMatrixTimesMatrix(long resultType, long result, long leftMatrix, long rightMatrix) {

    }

    @Override
    public void visitOuterProduct(long resultType, long result, long vector1, long vector2) {

    }

    @Override
    public void visitDot(long resultType, long result, long vector1, long vector2) {

    }

    @Override
    public void visitIAddCarry(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitISubBorrow(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitUMulExtended(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitSMulExtended(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitShiftRightLogical(long resultType, long result, long base, long shift) {

    }

    @Override
    public void visitShiftRightArithmetic(long resultType, long result, long base, long shift) {

    }

    @Override
    public void visitShiftLeftLogical(long resultType, long result, long base, long shift) {

    }

    @Override
    public void visitBitwiseOr(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitBitwiseXor(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitBitwiseAnd(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitNot(long resultType, long result, long operand) {

    }

    @Override
    public void visitBitFieldInsert(long resultType, long result, long base, long insert, long offset, long count) {

    }

    @Override
    public void visitBitFieldSExtract(long resultType, long result, long base, long offset, long count) {

    }

    @Override
    public void visitBitFieldUExtract(long resultType, long result, long base, long offset, long count) {

    }

    @Override
    public void visitBitReverse(long resultType, long result, long base) {

    }

    @Override
    public void visitBitCount(long resultType, long result, long base) {

    }

    @Override
    public void visitAny(long resultType, long result, long vector) {

    }

    @Override
    public void visitAll(long resultType, long result, long vector) {

    }

    @Override
    public void visitIsNan(long resultType, long result, long x) {

    }

    @Override
    public void visitIsInf(long resultType, long result, long x) {

    }

    @Override
    public void visitIsFinite(long resultType, long result, long x) {

    }

    @Override
    public void visitIsNormal(long resultType, long result, long x) {

    }

    @Override
    public void visitSignBitSet(long resultType, long result, long x) {

    }

    @Override
    public void visitLessOrGreater(long resultType, long result, long x, long y) {

    }

    @Override
    public void visitOrdered(long resultType, long result, long x, long y) {

    }

    @Override
    public void visitUnordered(long resultType, long result, long x, long y) {

    }

    @Override
    public void visitLogicalEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitLogicalNotEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitLogicalOr(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitLogicalAnd(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitLogicalNot(long resultType, long result, long operand) {

    }

    @Override
    public void visitSelect(long resultType, long result, long condition, long object1, long object2) {

    }

    @Override
    public void visitIEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitINotEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitUGreaterThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitSGreaterThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitUGreaterThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitSGreaterThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitULessThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitSLessThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitULessThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitSLessThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdNotEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordNotEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdLessThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordLessThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdGreaterThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordGreaterThan(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdLessThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordLessThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFOrdGreaterThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitFUnordGreaterThanEqual(long resultType, long result, long operand1, long operand2) {

    }

    @Override
    public void visitDPdx(long resultType, long result, long p) {

    }

    @Override
    public void visitDPdy(long resultType, long result, long p) {

    }

    @Override
    public void visitFwidth(long resultType, long result, long p) {

    }

    @Override
    public void visitDPdxFine(long resultType, long result, long p) {

    }

    @Override
    public void visitDPdyFine(long resultType, long result, long p) {

    }

    @Override
    public void visitFwidthFine(long resultType, long result, long p) {

    }

    @Override
    public void visitDPdxCoarse(long resultType, long result, long p) {

    }

    @Override
    public void visitDPdyCoarse(long resultType, long result, long p) {

    }

    @Override
    public void visitFwidthCoarse(long resultType, long result, long p) {

    }

    @Override
    public void visitPhi(long resultType, long result, long[] variables) {

    }

    @Override
    public void visitLoopMerge(long mergeBlock, long continueTarget, long loopControl, long[] parameters) {

    }

    @Override
    public void visitSelectionMerge(long mergeBlock, long selectionControl) {

    }

    @Override
    public void visitCompositeExtract(long resultTypeID, long resultID, long compositeID, long[] indexes) {

    }

    @Override
    public void visitCompositeInsert(long resultType, long result, long object, long composite, long[] indexes) {

    }

    @Override
    public void visitCopyObject(long resultType, long result, long operand) {

    }

    @Override
    public void visitTranspose(long resultType, long result, long matrix) {

    }
}

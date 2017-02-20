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
    public void visitSpecConstantBool(long resultType, long resultID, boolean defaultValue) {

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
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {

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
    public void visitExtension(String extension) {

    }

    @Override
    public void visitReturn() {

    }

    @Override
    public void visitEnd() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void visitUndef(long resultType, long resultID) {

    }

    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {

    }

    @Override
    public void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands) {

    }

    @Override
    public void visitKill() {

    }

    @Override
    public void visitReturnValue(long valueID) {

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
    public void visitCompositeExtract(long resultTypeID, long resultID, long compositeID, long[] indexes) {

    }
}

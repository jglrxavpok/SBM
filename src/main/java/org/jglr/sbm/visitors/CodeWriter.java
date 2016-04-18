package org.jglr.sbm.visitors;

import org.jglr.flows.io.ByteArray;
import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.image.*;

import java.nio.ByteOrder;
import java.util.Map;

public class CodeWriter implements CodeVisitor, Opcodes {

    private final ByteArray buffer;

    public CodeWriter() {
        buffer = new ByteArray();
        buffer.setByteOrder(ByteOrder.BIG_ENDIAN);
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
    public void visitTrueConstant(long type, long resultID) {

    }

    @Override
    public void visitFalseConstant(long type, long resultID) {

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
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {

    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {

    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {

    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {

    }

    @Override
    public void visitFunction(long resultType, long resultID, FunctionControl control, long funcType) {
        newOpcode(OpFunction, 4);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(control.getMask());
        buffer.putUnsignedInt(funcType);
    }

    @Override
    public void visitFunctionEnd() {

    }

    @Override
    public void visitStore(long pointer, long object, MemoryAccess memoryAccess) {

    }

    @Override
    public void visitAccessChain(long resultType, long resultID, long base, long[] indexes) {

    }

    @Override
    public void visitLabel(long resultID) {
        newOpcode(OpLabel, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitExtension(String extension) {

    }

    @Override
    public void visitReturn() {
        newOpcode(OpReturn, 0);
    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {

    }

    @Override
    public void visitEnd() {

    }

    @Override
    public void reset() {
        buffer.reset();
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
        newOpcode(OpTypeFunction, 2 + parameterTypes.length);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(returnType);
        for (long l : parameterTypes)
            buffer.putUnsignedInt(l);
    }

    @Override
    public void visitEventType(long resultID) {

    }

    @Override
    public void visitDeviceEventType(long resultID) {

    }

    @Override
    public void visitReserveIDType(long resultID) {

    }

    @Override
    public void visitQueueType(long resultID) {

    }

    @Override
    public void visitPipeType(long resultID, AccessQualifier accessQualifier) {

    }

    @Override
    public void visitForwardType(long type, StorageClass storageClass) {

    }

    public byte[] toBytes() {
        return buffer.backingArray();
    }

    private void newOpcode(int opcode, int argCount) {
        buffer.putUnsignedInt(((long)(argCount+1) << 16L | opcode));
    }
}

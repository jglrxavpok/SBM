package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.instructions.*;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CodeCollector implements SBMCodeVisitor {

    private final StringBuffer buffer;
    private final List<SpvInstruction> instructions;
    private ConstantPool constantPool;

    public CodeCollector() {
        buffer = new StringBuffer();
        instructions = new LinkedList<>();
        constantPool = new ConstantPool();
    }

    @Override
    public void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode) {
        addInstruction(new SourceInstruction(language, version, filenameStringID, sourceCode));
    }

    @Override
    public void visitSourceContinued(String source) {
        addInstruction(new SourceContinuedInstruction(source));
    }

    @Override
    public void visitSourceExtension(String extension) {
        addInstruction(new ExtensionImportInstruction(extension));
    }

    @Override
    public void visitLine(long filenameID, long line, long column) {
        addInstruction(new LineInstruction(filenameID, line, column));
    }

    @Override
    public void visitName(long target, String name) {
        constantPool.registerName(target, name);
        addInstruction(new NameInstruction(target, name));
    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {
        constantPool.registerMemberName(structureType, target, name);
        addInstruction(new MemberNameInstruction(structureType, target, name));
    }

    @Override
    public void visitString(long resultID, String value) {
        constantPool.registerString(resultID, value);
        addInstruction(new StringInstruction(resultID, value));
    }

    @Override
    public void visitTrueConstant(long type, long resultID) {
        addInstruction(new BooleanConstantInstruction(type, resultID, true));
    }

    @Override
    public void visitFalseConstant(long type, long resultID) {
        addInstruction(new BooleanConstantInstruction(type, resultID, false));
    }

    @Override
    public void visitCapability(Capability cap) {
        addInstruction(new CapabilityInstruction(cap));
    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {
        addInstruction(new ExtendedInstructionSetImportInstruction(resultID, name));
        constantPool.registerSet(resultID, name);
    }

    @Override
    public void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands) {
        addInstruction(new ExtendedInstructionSetCallInstruction(resultType, resultID, set, instruction, operands));
    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        addInstruction(new MemoryModelInstruction(addressingModel, memoryModel));
    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {
        addInstruction(new EntryPointInstruction(model, entryPoint, name, interfaces));
    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {
        addInstruction(new VariableInstruction(resultType, resultID, storageClass, initializer));
    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {
        addInstruction(new ConstantInstruction(type, resultID, bitPattern));
    }

    @Override
    public void visitFunction(long resultType, long resultID, FunctionControl control, long funcType) {
        addInstruction(new FunctionInstruction(resultType, resultID, control, funcType));
    }

    @Override
    public void visitFunctionEnd() {
        addInstruction(new FunctionEndInstruction());
    }

    @Override
    public void visitIntDecoration(Decoration decoration, long target, long value) {
        addInstruction(new IntDecorationInstruction(decoration, target, value));
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
        addInstruction(new DecorationInstruction(2, decoration, target));
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
        addInstruction(new VoidTypeInstruction(resultID));
        constantPool.registerType(resultID, new Type("void"));
    }

    @Override
    public void visitBoolType(long resultID) {
        addInstruction(new BoolTypeInstruction(resultID));
        constantPool.registerType(resultID, new Type("bool"));
    }

    @Override
    public void visitFloatType(long resultID, long width) {
        addInstruction(new FloatTypeInstruction(resultID, width));
        constantPool.registerType(resultID, new FloatType(width));
    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {
        addInstruction(new IntTypeInstruction(resultID, width, isSigned));
        constantPool.registerType(resultID, new IntType(width, isSigned));
    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {
        addInstruction(new VectorTypeInstruction(resultID, componentType, componentCount));
        constantPool.registerType(resultID, new VectorType(constantPool.getType(componentType), componentCount));
    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {
        addInstruction(new MatrixTypeInstruction(resultID, columnType, columnCount));
        constantPool.registerType(resultID, new MatrixType(constantPool.getType(columnType), columnCount));
    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {
        addInstruction(new ImageTypeInstruction(resultID, sampledType, dim, depth, arrayed, multisampled, sampling, format, access));
        constantPool.registerType(resultID, new ImageType(constantPool.getType(sampledType), dim, depth, arrayed, multisampled, sampling, format, access));
    }

    @Override
    public void visitSamplerType(long resultID) {
        addInstruction(new SamplerTypeInstruction(resultID));
        constantPool.registerType(resultID, new Type("sampler"));
    }

    @Override
    public void visitSampledImageType(long resultID, long imageType) {
        addInstruction(new SampledImageTypeInstruction(resultID, imageType));
        constantPool.registerType(resultID, new SampledImageType(constantPool.getType(imageType)));
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
        addInstruction(new PointerTypeInstruction(resultID, storageClass, type));
        constantPool.registerType(resultID, new PointerType(storageClass, constantPool.getType(type)));
    }

    @Override
    public void visitFunctionType(long resultID, long returnType, long[] parameterTypes) {
        addInstruction(new FunctionTypeInstruction(resultID, returnType, parameterTypes));
        constantPool.registerType(resultID, new FunctionType(constantPool.getType(returnType), constantPool.getTypes(parameterTypes)));
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

    @Override
    public void visitStore(long pointer, long object, MemoryAccess memoryAccess) {
        addInstruction(new StoreInstruction(pointer, object, memoryAccess));
    }

    @Override
    public void visitAccessChain(long resultType, long resultID, long base, long[] indexes) {
        addInstruction(new AccessChainInstruction(resultType, resultID, base, indexes));
    }

    @Override
    public void visitLabel(long resultID) {
        addInstruction(new LabelInstruction(resultID));
    }

    @Override
    public void visitExtension(String extension) {
        addInstruction(new ExtensionUseInstruction(extension));
    }

    @Override
    public void visitReturn() {
        addInstruction(new ReturnInstruction());
    }

    private void addInstruction(SpvInstruction instruction) {
        // TODO: check if visit ended?
        instructions.add(instruction);
    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {
        addInstruction(new LoadInstruction(resultType, resultID, pointer, memoryAccess));
    }

    @Override
    public void visitEnd() {
        instructions.stream()
                .filter(i -> i instanceof ResolvableInstruction)
                .map(i -> (ResolvableInstruction)i)
                .forEach(i -> {
                    i.onVisitEnd(constantPool);
                });
    }

    @Override
    public void reset() {
        instructions.clear();
        constantPool.empty();
    }

    public List<SpvInstruction> getInstructions() {
        return instructions;
    }

    public String getResult() {
        return buffer.toString();
    }
}

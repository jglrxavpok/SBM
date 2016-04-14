package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.types.Type;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;

import java.util.Arrays;
import java.util.HashMap;

public class CodeCollector implements SBMCodeVisitor {

    private final HashMap<Long, String> strings;
    private final HashMap<Long, String> names;
    private final StringBuffer buffer;

    public CodeCollector() {
        strings = new HashMap<>();
        names = new HashMap<>();
        buffer = new StringBuffer();
    }

    private String getName(long id) {
        return names.getOrDefault(id, null);
    }

    private String getString(long id) {
        return strings.getOrDefault(id, null);
    }

    @Override
    public void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode) {
        String filename = getString(filenameStringID);
        buffer.append("Source: ").append(language).append(" v").append(version).append(", filename: ").append(filename).append(", code: ").append(sourceCode).append('\n');
    }

    @Override
    public void visitSourceContinued(String source) {
        buffer.append("Source continued: ").append(source).append('\n');
    }

    @Override
    public void visitSourceExtension(String source) {
        buffer.append("Extension: ").append(source).append('\n');
    }

    @Override
    public void visitLine(long filenameID, long line, long column) {
        buffer.append("Line (").append(line).append(", ").append(column).append(")").append('\n');
    }

    @Override
    public void visitName(long target, String name) {
        names.put(target, name);
        System.out.println("Name at index "+target+": "+name);
        buffer.append("Name: ").append(name).append(" for %").append(target).append('\n');
    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {
        System.out.println("MemberName at index "+target+": "+name+", for type %"+structureType);
        buffer.append("MemberName: ").append(name).append(" for %").append(target).append(" inside %").append(structureType).append('\n');
    }

    @Override
    public void visitString(long resultID, String value) {
        strings.put(resultID, value);
        buffer.append("%").append(resultID).append(" = String: ").append(value).append('\n');
    }

    @Override
    public void visitTrueConstant(long type, long resultID) {
        buffer.append("%").append(resultID).append(" = TrueConstant").append('\n');
    }

    @Override
    public void visitFalseConstant(long type, long resultID) {
        buffer.append("%").append(resultID).append(" = FalseConstant").append('\n');
    }

    @Override
    public void visitCapability(Capability cap) {
        System.out.println("Capability: "+cap.name());
        buffer.append("Capability: ").append(cap.name()).append('\n');
    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {
        System.out.println(">> ext: "+name);
        buffer.append("%").append(resultID).append(" ExtImport = ").append(name).append('\n');
    }

    @Override
    public void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands) {
        System.out.println("Extended: %"+resultType+", resultID: "+resultID+", set: "+set+", instruction: "+instruction+", operands: "+Arrays.toString(operands));
        buffer.append("%").append(resultID).append(" ExtIsn = ").append(set).append(".").append(instruction).append(Arrays.toString(operands)).append('\n');
    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        buffer.append("MemoryModel = ").append(addressingModel).append(", ").append(memoryModel).append('\n');
    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {
        System.out.println("entry point: "+model+", point: "+entryPoint+", name: "+name+", interfaces: "+Arrays.toString(interfaces));
        buffer.append("EntryPoint = %").append(entryPoint).append("\"").append(name).append("\"").append(Arrays.toString(interfaces)).append('\n');
    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {
        System.out.println("New var of type "+resultType+" at index "+resultID+", name: "+getName(resultID)+", init: "+initializer);
        buffer.append("%").append(resultID).append(" = Variable ").append(storageClass).append(' ').append(getName(resultID)).append('\n');
    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {
        System.out.println("New constant of type "+type+" at index "+resultID+", pattern: "+ Arrays.toString(bitPattern));
        buffer.append("%").append(resultID).append(" = Constant %").append(type).append(" ").append(Arrays.toString(bitPattern)).append('\n');
    }

    @Override
    public void visitFunction(long resultType, long resultID, FunctionControl control, long funcType) {
        System.out.println("New function: "+resultType+", index: "+resultID+", type: "+funcType+", name: "+getName(resultID));
        buffer.append("%").append(resultID).append(" = Function %").append(resultType).append(" / %").append(funcType).append(" 0x").append(Long.toHexString(control.getMask())).append('\n');
    }

    @Override
    public void visitFunctionEnd() {
        buffer.append("FunctionEnd").append('\n');
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
        buffer.append("%").append(resultID).append(" = void").append('\n');
    }

    @Override
    public void visitBoolType(long resultID) {
        buffer.append("%").append(resultID).append(" = bool").append('\n');
    }

    @Override
    public void visitFloatType(long resultID, long width) {
        buffer.append("%").append(resultID).append(" = float").append(width).append('\n');
    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {
        buffer.append("%").append(resultID).append(" = ");
        if(!isSigned)
            buffer.append('u');
        buffer.append("int").append(width).append('\n');
    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {
        buffer.append("%").append(resultID).append(" = vec").append(componentCount).append("(%").append(componentType).append(")").append('\n');
    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {
        buffer.append("%").append(resultID).append(" = mat").append(columnCount).append("(%").append(columnType).append(")").append('\n');
    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {

    }

    @Override
    public void visitSamplerType(long resultID) {
        buffer.append("%").append(resultID).append(" = sampler").append('\n');
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
        buffer.append("Store %").append(object).append(" -> %").append(pointer).append('\n');
    }

    @Override
    public void visitAccessChain(long resultType, long resultID, long base, long[] indexes) {
        buffer.append("%").append(resultID).append(" = AccessChain %").append(resultType).append(" %").append(base).append(' ').append(Arrays.toString(indexes)).append('\n');
    }

    @Override
    public void visitLabel(long resultID) {
        buffer.append('%').append(resultID).append(" = Label").append('\n');
    }

    @Override
    public void visitExtension(String extension) {
        buffer.append("Extension: ").append(extension);
    }

    @Override
    public void visitReturn() {
        buffer.append("Return").append('\n');
    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {
        buffer.append('%').append(resultID).append(" = OpLoad %").append(pointer).append(" (").append(Long.toHexString(memoryAccess.getMask())).append(")").append('\n');
    }

    public String getResult() {
        return buffer.toString();
    }
}

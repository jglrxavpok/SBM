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

    public CodeCollector() {
        strings = new HashMap<>();
        names = new HashMap<>();
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
        System.out.println("Source: "+language+" v"+version+", filename: "+filename+", code: "+sourceCode);
    }

    @Override
    public void visitSourceContinued(String source) {
        System.out.println(">>continued: "+source);
    }

    @Override
    public void visitSourceExtension(String source) {
        System.out.println(">>extension: "+source);
    }

    @Override
    public void visitLine(long filenameID, long line, long column) {

    }

    @Override
    public void visitName(long target, String name) {
        names.put(target, name);
        System.out.println("Name at index "+target+": "+name);
    }

    @Override
    public void visitMemberName(Type structureType, long target, String name) {
        System.out.println("MemberName at index "+target+": "+name+", for type "+structureType);
    }

    @Override
    public void visitString(long resultID, String value) {
        strings.put(resultID, value);
    }

    @Override
    public void visitTrueConstant(long resultID) {

    }

    @Override
    public void visitFalseConstant(long resultID) {

    }

    @Override
    public void visitCapability(Capability cap) {
        System.out.println("Capability: "+cap.name());
    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {
        System.out.println(">> ext: "+name);
    }

    @Override
    public void visitExecExtendedInstruction(Type resultType, long resultID, long set, long instruction, long[] operands) {
        System.out.println("Extended: "+resultType+", resultID: "+resultID+", set: "+set+", instruction: "+instruction+", operands: "+Arrays.toString(operands));
    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {

    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {
        System.out.println("entry point: "+model+", point: "+entryPoint+", name: "+name+", interfaces: "+Arrays.toString(interfaces));
    }

    @Override
    public void visitVariable(Type resultType, long resultID, StorageClass storageClass, long initializer) {
        System.out.println("New var of type "+resultType+" at index "+resultID+", name: "+getName(resultID)+", init: "+initializer);
    }

    @Override
    public void visitConstant(Type type, long resultID, long[] bitPattern) {
        System.out.println("New constant of type "+type+" at index "+resultID+", pattern: "+ Arrays.toString(bitPattern));
    }

    @Override
    public void visitFunction(Type resultType, long resultID, FunctionControl control, Type funcType) {
        System.out.println("New function: "+resultType+", index: "+resultID+", type: "+funcType+", name: "+getName(resultID));
    }

    @Override
    public void visitFunctionEnd() {

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
    public void visitIntMemberDecoration(Decoration decoration, Type structureType, long member, long value) {

    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(Type structureType, long member, FunctionParameterAttribute attribute) {

    }

    @Override
    public void visitFPRoundingModeMemberDecoration(Type structureType, long member, FPRoundingMode roundingMode) {

    }

    @Override
    public void visitFPFastMathModeMemberDecoration(Type structureType, long member, FPFastMathMode mathMode) {

    }

    @Override
    public void visitLinkageAttributesMemberDecoration(Type structureType, long member, String name, LinkageType linkageType) {

    }

    @Override
    public void visitMemberDecoration(Type structureType, long member, Decoration decoration) {

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
    public void visitVectorType(long resultID, Type componentType, long componentCount) {

    }

    @Override
    public void visitMatrixType(long resultID, Type columnType, long columnCount) {

    }

    @Override
    public void visitImageType(long resultID, Type sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {

    }

    @Override
    public void visitSamplerType(long resultID) {

    }

    @Override
    public void visitSampledImageType(long resultID, Type imageType) {

    }

    @Override
    public void visitArrayType(long resultID, Type elementType, long length) {

    }

    @Override
    public void visitRuntimeArrayType(long resultID, Type elementType) {

    }

    @Override
    public void visitStructType(long resultID, Type[] memberTypes) {

    }

    @Override
    public void visitOpaqueType(long resultID, String name) {

    }

    @Override
    public void visitPointerType(long resultID, StorageClass storageClass, Type type) {

    }

    @Override
    public void visitFunctionType(long resultID, Type returnType, Type[] parameterTypes) {

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
    public void visitForwardType(Type type, StorageClass storageClass) {

    }
}

package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.types.Type;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;

public class CodeCollector implements SBMCodeVisitor {
    @Override
    public void visitVoidType(int resultID) {

    }

    @Override
    public void visitBoolType(int resultID) {

    }

    @Override
    public void visitFloatType(int resultID, int width) {

    }

    @Override
    public void visitIntType(int resultID, int width, boolean signed) {

    }

    @Override
    public void visitVectorType(int resultID, Type componentType, int componentCount) {

    }

    @Override
    public void visitMatrixType(int resultID, Type columnType, int columnCount) {

    }

    @Override
    public void visitImageType(int resultID, Type sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {

    }

    @Override
    public void visitSamplerType(int resultID) {

    }

    @Override
    public void visitSampledImageType(int resultID, Type imageType) {

    }

    @Override
    public void visitArrayType(int resultID, Type elementType, int length) {

    }

    @Override
    public void visitRuntimeArrayType(int resultID, Type elementType) {

    }

    @Override
    public void visitStructType(int resultID, Type[] memberTypes) {

    }

    @Override
    public void visitOpaqueType(int resultID, String name) {

    }

    @Override
    public void visitPointerType(int resultID, StorageClass storageClass, Type type) {

    }

    @Override
    public void visitFunctionType(int resultID, Type returnType, Type[] parameterTypes) {

    }

    @Override
    public void visitEventType(int resultID) {

    }

    @Override
    public void visitDeviceEventType(int resultID) {

    }

    @Override
    public void visitReserveIDType(int resultID) {

    }

    @Override
    public void visitQueueType(int resultID) {

    }

    @Override
    public void visitPipeType(int resultID, AccessQualifier accessQualifier) {

    }

    @Override
    public void visitForwardType(Type type, StorageClass storageClass) {

    }

    @Override
    public void visitSource(SourceLanguage language, int version, int filenameStringID, String sourceCode) {
        System.out.println("Source: "+language+" v"+version+", id: "+filenameStringID+", code: "+sourceCode);
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
    public void visitLine(int filenameID, int line, int column) {

    }

    @Override
    public void visitName(int target, String name) {

    }

    @Override
    public void visitMemberName(Type structureType, int target, String name) {

    }

    @Override
    public void visitString(int resultID, String value) {

    }

    @Override
    public void visitTrueConstant(int resultID) {

    }

    @Override
    public void visitFalseConstant(int resultID) {

    }

    @Override
    public void visitCapability(Capability cap) {

    }

    @Override
    public void visitExtendedInstructionSetImport(int resultID, String name) {
        System.out.println(">> ext: "+name);
    }

    @Override
    public void visitExecExtendedInstruction(Type resultType, int resultID, int set, int instruction, int[] operands) {

    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {

    }

    @Override
    public void visitEntryPoint(ExecutionModel model, int entryPoint, String name, int[] interfaces) {
        System.out.println("entry point: "+model+", point: "+entryPoint+", name: "+name);
    }

    @Override
    public void visitIntDecoration(Decoration decoration, int target, int value) {

    }

    @Override
    public void visitFunctionParameterAttributeDecoration(int target, FunctionParameterAttribute attribute) {

    }

    @Override
    public void visitFPRoundingModeDecoration(int target, FPRoundingMode roundingMode) {

    }

    @Override
    public void visitFPFastMathModeDecoration(int target, FPFastMathMode fastMathMode) {

    }

    @Override
    public void visitLinkageAttributesDecoration(int target, String name, LinkageType type) {

    }

    @Override
    public void visitDecoration(int target, Decoration decoration) {

    }

    @Override
    public void visitIntMemberDecoration(Decoration decoration, Type structureType, int member, int value) {

    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(Type structureType, int member, FunctionParameterAttribute attribute) {

    }

    @Override
    public void visitFPRoundingModeMemberDecoration(Type structureType, int member, FPRoundingMode roundingMode) {

    }

    @Override
    public void visitFPFastMathModeMemberDecoration(Type structureType, int member, FPFastMathMode mathMode) {

    }

    @Override
    public void visitLinkageAttributesMemberDecoration(Type structureType, int member, String name, LinkageType linkageType) {

    }

    @Override
    public void visitMemberDecoration(Type structureType, int member, Decoration decoration) {

    }
}

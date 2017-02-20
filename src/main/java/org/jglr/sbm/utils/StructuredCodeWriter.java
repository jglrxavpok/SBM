package org.jglr.sbm.utils;

import org.jglr.flows.io.ByteArray;
import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.sampler.*;
import org.jglr.sbm.visitors.CodeWriter;

import java.nio.ByteOrder;
import java.util.*;

/**
 * Makes the written code follow the logical layout of a valid SPIR-V module.
 * <a href="https://www.khronos.org/registry/spir-v/specs/1.1/SPIRV.html#_a_id_logicallayout_a_logical_layout_of_a_module">2.4. Logical Layout of a Module</a>
 */
public class StructuredCodeWriter extends CodeWriter {

    // TODO: OpLine

    private final ByteArray capabilities;
    private final ByteArray instructionsImports;
    private final ByteArray extensions;
    private final ByteArray nameInstructions;
    private final ByteArray memoryModel;
    private final ByteArray entryPoints;
    private final ByteArray executionModes;
    private final ByteArray stringAndSources;
    private final ByteArray annotations;
    private final ByteArray typeDeclarations;
    private final ByteArray constants;
    private final ByteArray globalVars;
    private final List<ByteArray> functions;
    private ByteArray last;
    private final ByteArray miscStuff;

    public StructuredCodeWriter() {
        capabilities = new ByteArray();
        extensions = new ByteArray();
        instructionsImports = new ByteArray();
        memoryModel = new ByteArray();
        entryPoints = new ByteArray();
        executionModes = new ByteArray();
        stringAndSources = new ByteArray();
        nameInstructions = new ByteArray();
        annotations = new ByteArray();
        typeDeclarations = new ByteArray();
        constants = new ByteArray();
        globalVars = new ByteArray();
        miscStuff = new ByteArray();
        functions = new LinkedList<>();
        switchTo(miscStuff);
    }

    private void switchTo(ByteArray buffer) {
        last = this.buffer;
        buffer.setByteOrder(ByteOrder.BIG_ENDIAN);
        this.buffer = buffer;
    }

    @Override
    public void visitFunction(long resultType, long resultID, FunctionControl control, long funcType) {
        ByteArray functionBuffer = new ByteArray();
        functions.add(functionBuffer);
        switchTo(functionBuffer);
        super.visitFunction(resultType, resultID, control, funcType);
    }

    @Override
    public void visitFunctionEnd() {
        super.visitFunctionEnd();
        switchTo(last);
    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {
        if(storageClass != StorageClass.Function) {
            switchTo(globalVars);
        }
        super.visitVariable(resultType, resultID, storageClass, initializer);
        if(storageClass != StorageClass.Function) {
            switchTo(last);
        }
    }

    @Override
    public void visitConstantTrue(long type, long resultID) {
        switchTo(constants);
        super.visitConstantTrue(type, resultID);
        switchTo(last);
    }

    @Override
    public void visitConstantFalse(long type, long resultID) {
        switchTo(constants);
        super.visitConstantFalse(type, resultID);
        switchTo(last);
    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {
        switchTo(constants);
        super.visitConstant(type, resultID, bitPattern);
        switchTo(last);
    }

    @Override
    public void visitConstantComposite(long resultType, long resultID, long[] constituents) {
        switchTo(constants);
        super.visitConstantComposite(resultType, resultID, constituents);
        switchTo(last);
    }

    @Override
    public void visitConstantSampler(long resultType, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter) {
        switchTo(constants);
        super.visitConstantSampler(resultType, resultID, mode, normalized, filter);
        switchTo(last);
    }

    @Override
    public void visitConstantNull(long resultType, long resultID) {
        switchTo(constants);
        super.visitConstantNull(resultType, resultID);
        switchTo(last);
    }

    @Override
    public void visitSpecConstantBool(long resultType, long resultID, boolean defaultValue) {
        switchTo(constants);
        super.visitSpecConstantBool(resultType, resultID, defaultValue);
        switchTo(last);
    }

    @Override
    public void visitSpecConstant(long resultType, long resultID, long[] value) {
        switchTo(constants);
        super.visitSpecConstant(resultType, resultID, value);
        switchTo(last);
    }

    @Override
    public void visitSpecConstantComposite(long resultType, long resultID, long[] constituents) {
        switchTo(constants);
        super.visitSpecConstantComposite(resultType, resultID, constituents);
        switchTo(last);
    }

    @Override
    public void visitSpecConstantOp(long resultType, long resultID, long opcode, long[] operands) {
        switchTo(constants);
        super.visitSpecConstantOp(resultType, resultID, opcode, operands);
        switchTo(last);
    }


    @Override
    public void visitVoidType(long resultID) {
        switchTo(typeDeclarations);
        super.visitVoidType(resultID);
        switchTo(last);
    }

    @Override
    public void visitBoolType(long resultID) {
        switchTo(typeDeclarations);
        super.visitBoolType(resultID);
        switchTo(last);
    }

    @Override
    public void visitFloatType(long resultID, long width) {
        switchTo(typeDeclarations);
        super.visitFloatType(resultID, width);
        switchTo(last);
    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {
        switchTo(typeDeclarations);
        super.visitIntType(resultID, width, isSigned);
        switchTo(last);
    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {
        switchTo(typeDeclarations);
        super.visitVectorType(resultID, componentType, componentCount);
        switchTo(last);
    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {
        switchTo(typeDeclarations);
        super.visitMatrixType(resultID, columnType, columnCount);
        switchTo(last);
    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {
        switchTo(typeDeclarations);
        super.visitImageType(resultID, sampledType, dim, depth, arrayed, multisampled, sampling, format, access);
        switchTo(last);
    }

    @Override
    public void visitSamplerType(long resultID) {
        switchTo(typeDeclarations);
        super.visitSamplerType(resultID);
        switchTo(last);
    }

    @Override
    public void visitSampledImageType(long resultID, long imageType) {
        switchTo(typeDeclarations);
        super.visitSampledImageType(resultID, imageType);
        switchTo(last);
    }

    @Override
    public void visitArrayType(long resultID, long elementType, long length) {
        switchTo(typeDeclarations);
        super.visitArrayType(resultID, elementType, length);
        switchTo(last);
    }

    @Override
    public void visitRuntimeArrayType(long resultID, long elementType) {
        switchTo(typeDeclarations);
        super.visitRuntimeArrayType(resultID, elementType);
        switchTo(last);
    }

    @Override
    public void visitStructType(long resultID, long[] memberTypes) {
        switchTo(typeDeclarations);
        super.visitStructType(resultID, memberTypes);
        switchTo(last);
    }

    @Override
    public void visitOpaqueType(long resultID, String name) {
        switchTo(typeDeclarations);
        super.visitOpaqueType(resultID, name);
        switchTo(last);
    }

    @Override
    public void visitPointerType(long resultID, StorageClass storageClass, long type) {
        switchTo(typeDeclarations);
        super.visitPointerType(resultID, storageClass, type);
        switchTo(last);
    }

    @Override
    public void visitFunctionType(long resultID, long returnType, long[] parameterTypes) {
        switchTo(typeDeclarations);
        super.visitFunctionType(resultID, returnType, parameterTypes);
        switchTo(last);
    }

    @Override
    public void visitEventType(long resultID) {
        switchTo(typeDeclarations);
        super.visitEventType(resultID);
        switchTo(last);
    }

    @Override
    public void visitDeviceEventType(long resultID) {
        switchTo(typeDeclarations);
        super.visitDeviceEventType(resultID);
        switchTo(last);
    }

    @Override
    public void visitReserveIdType(long resultID) {
        switchTo(typeDeclarations);
        super.visitReserveIdType(resultID);
        switchTo(last);
    }

    @Override
    public void visitQueueType(long resultID) {
        switchTo(typeDeclarations);
        super.visitQueueType(resultID);
        switchTo(last);
    }

    @Override
    public void visitPipeType(long resultID, AccessQualifier accessQualifier) {
        switchTo(typeDeclarations);
        super.visitPipeType(resultID, accessQualifier);
        switchTo(last);
    }

    @Override
    public void visitForwardPointerType(long type, StorageClass storageClass) {
        switchTo(typeDeclarations);
        super.visitForwardPointerType(type, storageClass);
        switchTo(last);
    }

    @Override
    public void visitNamedBarrierType(long resultID) {
        switchTo(typeDeclarations);
        super.visitNamedBarrierType(resultID);
        switchTo(last);
    }

    @Override
    public void visitPipeStorageType(long resultID) {
        switchTo(typeDeclarations);
        super.visitPipeStorageType(resultID);
        switchTo(last);
    }

    @Override
    public void visitIntDecoration(Decoration decoration, long target, long value) {
        switchTo(annotations);
        super.visitIntDecoration(decoration, target, value);
        switchTo(last);
    }

    @Override
    public void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute) {
        switchTo(annotations);
        super.visitFunctionParameterAttributeDecoration(target, attribute);
        switchTo(last);
    }

    @Override
    public void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode) {
        switchTo(annotations);
        super.visitFPRoundingModeDecoration(target, roundingMode);
        switchTo(last);
    }

    @Override
    public void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode) {
        switchTo(annotations);
        super.visitFPFastMathModeDecoration(target, fastMathMode);
        switchTo(last);
    }

    @Override
    public void visitLinkageAttributesDecoration(long target, String name, LinkageType type) {
        switchTo(annotations);
        super.visitLinkageAttributesDecoration(target, name, type);
        switchTo(last);
    }

    @Override
    public void visitDecoration(long target, Decoration decoration) {
        switchTo(annotations);
        super.visitDecoration(target, decoration);
        switchTo(last);
    }

    @Override
    public void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value) {
        switchTo(annotations);
        super.visitIntMemberDecoration(decoration, structureType, member, value);
        switchTo(last);
    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute) {
        switchTo(annotations);
        super.visitFunctionParameterAttributeMemberDecoration(structureType, member, attribute);
        switchTo(last);
    }

    @Override
    public void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode) {
        switchTo(annotations);
        super.visitFPRoundingModeMemberDecoration(structureType, member, roundingMode);
        switchTo(last);
    }

    @Override
    public void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode) {
        switchTo(annotations);
        super.visitFPFastMathModeMemberDecoration(structureType, member, mathMode);
        switchTo(last);
    }

    @Override
    public void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType) {
        switchTo(annotations);
        super.visitLinkageAttributesMemberDecoration(structureType, member, name, linkageType);
        switchTo(last);
    }

    @Override
    public void visitMemberDecoration(long structureType, long member, Decoration decoration) {
        switchTo(annotations);
        super.visitMemberDecoration(structureType, member, decoration);
        switchTo(last);
    }

    @Override
    public void visitDecorationGroup(long resultID) {
        switchTo(annotations);
        super.visitDecorationGroup(resultID);
        switchTo(last);
    }

    @Override
    public void visitGroupDecoration(long decorationGroup, long[] targets) {
        switchTo(annotations);
        super.visitGroupDecoration(decorationGroup, targets);
        switchTo(last);
    }

    @Override
    public void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets) {
        switchTo(annotations);
        super.visitGroupMemberDecoration(decorationGroup, memberTargets);
        switchTo(last);
    }

    @Override
    public void visitName(long target, String name) {
        switchTo(nameInstructions);
        super.visitName(target, name);
        switchTo(last);
    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {
        switchTo(nameInstructions);
        super.visitMemberName(structureType, target, name);
        switchTo(last);
    }

    @Override
    public void visitSourceContinued(String source) {
        switchTo(stringAndSources);
        super.visitSourceContinued(source);
        switchTo(last);
    }

    @Override
    public void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode) {
        switchTo(stringAndSources);
        super.visitSource(language, version, filenameStringID, sourceCode);
        switchTo(last);
    }

    @Override
    public void visitString(long resultID, String value) {
        switchTo(stringAndSources);
        super.visitString(resultID, value);
        switchTo(last);
    }

    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {
        switchTo(executionModes);
        super.visitExecutionMode(entryPoint, mode);
        switchTo(last);
    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {
        switchTo(entryPoints);
        super.visitEntryPoint(model, entryPoint, name, interfaces);
        switchTo(last);
    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        switchTo(this.memoryModel);
        this.memoryModel.setWriteCursor(0);
        super.visitMemoryModel(addressingModel, memoryModel);
        switchTo(last);
    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {
        switchTo(instructionsImports);
        super.visitExtendedInstructionSetImport(resultID, name);
        switchTo(last);
    }

    @Override
    public void visitExtension(String extension) {
        switchTo(extensions);
        super.visitExtension(extension);
        switchTo(last);
    }

    @Override
    public void visitSourceExtension(String source) {
        switchTo(stringAndSources);
        super.visitSourceExtension(source);
        switchTo(last);
    }

    @Override
    public void visitCapability(Capability cap) {
        switchTo(capabilities);
        super.visitCapability(cap);
        switchTo(last);
    }

    @Override
    public byte[] toBytes() {
        return ByteArray.joinAndReset(capabilities, extensions, instructionsImports, memoryModel, entryPoints, executionModes,
                stringAndSources, nameInstructions, annotations, typeDeclarations, constants, globalVars,
                ByteArray.joinAndReset(functions.toArray(new ByteArray[0]))).backingArray();
    }
}

package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.*;
import org.jglr.sbm.sampler.*;
import org.jglr.sbm.instructions.*;
import org.jglr.sbm.types.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CodeCollector implements CodeVisitor {

    private final List<SpvInstruction> instructions;
    private InfoPool infoPool;
    private boolean visitFinished;

    public CodeCollector() {
        instructions = new LinkedList<>();
        infoPool = new InfoPool();
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
        infoPool.registerName(target, name);
        addInstruction(new NameInstruction(target, name));
    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {
        infoPool.registerMemberName(structureType, target, name);
        addInstruction(new MemberNameInstruction(structureType, target, name));
    }

    @Override
    public void visitString(long resultID, String value) {
        infoPool.registerString(resultID, value);
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
        infoPool.registerSet(resultID, name);
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
        DecorationValue decorationValue = new IntDecorationValue(decoration, value);
        infoPool.addDecoration(target, decorationValue);
        addInstruction(new DecorationInstruction(3, decorationValue, target));
    }

    @Override
    public void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute) {
        DecorationValue decorationValue = new FunctionParameterAttributeDecorationValue(attribute);
        infoPool.addDecoration(target, decorationValue);
        addInstruction(new DecorationInstruction(3, decorationValue, target));
    }

    @Override
    public void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode) {
        DecorationValue decorationValue = new RoundingModeDecorationValue(roundingMode);
        infoPool.addDecoration(target, decorationValue);
        addInstruction(new DecorationInstruction(3, decorationValue, target));
    }

    @Override
    public void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode) {
        DecorationValue decorationValue = new FastMathDecorationValue(fastMathMode);
        infoPool.addDecoration(target, decorationValue);
        addInstruction(new DecorationInstruction(3, decorationValue, target));
    }

    @Override
    public void visitLinkageAttributesDecoration(long target, String name, LinkageType type) {
        DecorationValue decorationValue = new LinkageDecorationValue(name, type);
        infoPool.addDecoration(target, decorationValue);
        addInstruction(new DecorationInstruction(4 + name.length()/4, decorationValue, target));
    }

    @Override
    public void visitDecoration(long target, Decoration decoration) {
        addInstruction(new DecorationInstruction(2, new DecorationValue(decoration), target));
    }

    @Override
    public void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value) {
        DecorationValue decorationValue = new IntDecorationValue(decoration, value);
        addInstruction(new MemberDecorationInstruction(5, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute) {
        DecorationValue decorationValue = new FunctionParameterAttributeDecorationValue(attribute);
        addInstruction(new MemberDecorationInstruction(5, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode) {
        DecorationValue decorationValue = new RoundingModeDecorationValue(roundingMode);
        addInstruction(new MemberDecorationInstruction(5, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode) {
        DecorationValue decorationValue = new FastMathDecorationValue(mathMode);
        addInstruction(new MemberDecorationInstruction(5, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType) {
        DecorationValue decorationValue = new LinkageDecorationValue(name, linkageType);
        addInstruction(new MemberDecorationInstruction(6 + name.length()/4, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitMemberDecoration(long structureType, long member, Decoration decoration) {
        DecorationValue decorationValue = new DecorationValue(decoration);
        addInstruction(new MemberDecorationInstruction(4, decorationValue, structureType, member));
        infoPool.addMemberDecoration(structureType, member, decorationValue);
    }

    @Override
    public void visitDecorationGroup(long resultID) {
        addInstruction(new DecorationGroupInstruction(resultID));
    }

    @Override
    public void visitGroupDecoration(long decorationGroup, long[] targets) {
        addInstruction(new GroupDecorationInstruction(decorationGroup, targets));
    }

    @Override
    public void visitGroupMemberDecoration(long decorationGroup, long[] memberTargets) {
        addInstruction(new GroupDecorationMemberInstruction(decorationGroup, memberTargets));
    }

    @Override
    public void visitVoidType(long resultID) {
        addInstruction(new VoidTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("void"));
    }

    @Override
    public void visitBoolType(long resultID) {
        addInstruction(new BoolTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("bool"));
    }

    @Override
    public void visitFloatType(long resultID, long width) {
        addInstruction(new FloatTypeInstruction(resultID, width));
        infoPool.registerType(resultID, new FloatType(width));
    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {
        addInstruction(new IntTypeInstruction(resultID, width, isSigned));
        infoPool.registerType(resultID, new IntType(width, isSigned));
    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {
        addInstruction(new VectorTypeInstruction(resultID, componentType, componentCount));
        infoPool.registerType(resultID, new VectorType(infoPool.getType(componentType), componentCount));
    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {
        addInstruction(new MatrixTypeInstruction(resultID, columnType, columnCount));
        infoPool.registerType(resultID, new MatrixType(infoPool.getType(columnType), columnCount));
    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {
        addInstruction(new ImageTypeInstruction(resultID, sampledType, dim, depth, arrayed, multisampled, sampling, format, access));
        infoPool.registerType(resultID, new ImageType(infoPool.getType(sampledType), dim, depth, arrayed, multisampled, sampling, format, access));
    }

    @Override
    public void visitSamplerType(long resultID) {
        addInstruction(new SamplerTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("sampler"));
    }

    @Override
    public void visitSampledImageType(long resultID, long imageType) {
        addInstruction(new SampledImageTypeInstruction(resultID, imageType));
        infoPool.registerType(resultID, new SampledImageType(infoPool.getType(imageType)));
    }

    @Override
    public void visitArrayType(long resultID, long elementType, long length) {
        addInstruction(new ArrayTypeInstruction(resultID, elementType, length));
        infoPool.registerType(resultID, new ArrayType(infoPool.getType(elementType), length));
    }

    @Override
    public void visitRuntimeArrayType(long resultID, long elementType) {
        addInstruction(new RuntimeArrayTypeInstruction(resultID, elementType));
        infoPool.registerType(resultID, new RuntimeArrayType(infoPool.getType(elementType)));
    }

    @Override
    public void visitStructType(long resultID, long[] memberTypes) {
        addInstruction(new StructTypeInstruction(resultID, memberTypes));
        infoPool.registerType(resultID, new StructType(infoPool.getTypes(memberTypes)));
    }

    @Override
    public void visitOpaqueType(long resultID, String name) {
        addInstruction(new OpaqueTypeInstruction(resultID, name));
        infoPool.registerType(resultID, new OpaqueType(name));
    }

    @Override
    public void visitPointerType(long resultID, StorageClass storageClass, long type) {
        addInstruction(new PointerTypeInstruction(resultID, storageClass, type));
        infoPool.registerType(resultID, new PointerType(storageClass, infoPool.getType(type)));
    }

    @Override
    public void visitFunctionType(long resultID, long returnType, long[] parameterTypes) {
        addInstruction(new FunctionTypeInstruction(resultID, returnType, parameterTypes));
        infoPool.registerType(resultID, new FunctionType(infoPool.getType(returnType), infoPool.getTypes(parameterTypes)));
    }

    @Override
    public void visitEventType(long resultID) {
        addInstruction(new EventTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("event"));
    }

    @Override
    public void visitDeviceEventType(long resultID) {
        addInstruction(new DeviceEventTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("deviceEvent"));
    }

    @Override
    public void visitReserveIDType(long resultID) {
        addInstruction(new EventTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("reserveID"));
    }

    @Override
    public void visitQueueType(long resultID) {
        addInstruction(new QueueTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("queue"));
    }

    @Override
    public void visitPipeType(long resultID, AccessQualifier accessQualifier) {
        addInstruction(new PipeTypeInstruction(resultID, accessQualifier));
        infoPool.registerType(resultID, new PipeType(accessQualifier));
    }

    @Override
    public void visitForwardType(long type, StorageClass storageClass) {
        addInstruction(new ForwardPointerTypeInstruction(type, storageClass));
    }

    @Override
    public void visitNamedBarrierType(long resultID) {
        addInstruction(new NamedBarrierTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("namedBarrier"));
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
        if(visitFinished) {
            throw new IllegalStateException("Cannot visit instruction while visit has ended");
        }
        instructions.add(instruction);
    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {
        addInstruction(new LoadInstruction(resultType, resultID, pointer, memoryAccess));
    }

    @Override
    public void visitEnd() {
        visitFinished = true;
        instructions.stream()
                .filter(i -> i instanceof ResolvableInstruction)
                .map(i -> (ResolvableInstruction)i)
                .forEach(i -> i.onVisitEnd(infoPool));
    }

    @Override
    public void reset() {
        visitFinished = false;
        instructions.clear();
        infoPool.empty();
    }

    @Override
    public void visitUndef(long resultType, long resultID) {
        addInstruction(new UndefInstruction(resultType, resultID));
    }

    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {
        addInstruction(new ExecutionModeInstruction(entryPoint, mode));
        infoPool.setExecutionMode(entryPoint, mode);
    }

    @Override
    public void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands) {
        addInstruction(new ImageSampleImplicitLodInstruction(resultType, resultID, sampledImage, coordinate, operands, splitOperands));
    }

    @Override
    public void visitKill() {
        addInstruction(new KillInstruction());
    }

    @Override
    public void visitReturnValue(long valueID) {
        addInstruction(new ReturnValueInstruction(valueID));
    }

    @Override
    public void visitNoLine() {
        addInstruction(new NoLineInstruction());
    }

    @Override
    public void visitFunctionCall(long resultType, long resultID, long functionID, long[] arguments) {
        addInstruction(new FunctionCallInstruction(resultType, resultID, functionID, arguments));
    }

    @Override
    public void visitCopyMemory(long targetID, long sourceID, MemoryAccess access) {
        addInstruction(new CopyMemoryInstruction(sourceID, targetID, access));
    }

    @Override
    public void visitCopyMemorySized(long targetID, long sourceID, long size, MemoryAccess access) {
        addInstruction(new CopyMemorySizedInstruction(sourceID, targetID, size, access));
    }

    @Override
    public void visitModuleProcessed(String process) {
        addInstruction(new ModuleProcessedInstruction(process));
    }

    @Override
    public void visitConstantComposite(long resultType, long resultID, long[] constituents) {
        addInstruction(new ConstantCompositeInstruction(resultType, resultID, constituents));
    }

    @Override
    public void visitConstantSampler(long resultType, long resultID, SamplerAddressingMode mode, boolean normalized, SamplerFilterMode filter) {
        addInstruction(new ConstantSamplerInstruction(resultType, resultID, mode, normalized, filter));
    }

    @Override
    public void visitConstantNull(long resultType, long resultID) {
        addInstruction(new ConstantNullInstruction(resultType, resultID));
    }

    @Override
    public void visitSpecConstantBool(long resultType, long resultID, boolean defaultValue) {
        addInstruction(new SpecConstantBoolInstruction(resultType, resultID, defaultValue));
    }

    @Override
    public void visitSpecConstant(long resultType, long resultID, long[] value) {
        addInstruction(new SpecConstantInstruction(resultType, resultID, value));
    }

    @Override
    public void visitSpecConstantComposite(long resultType, long resultID, long[] constituents) {
        addInstruction(new SpecConstantCompositeInstruction(resultType, resultID, constituents));
    }

    @Override
    public void visitSpecConstantOp(long resultType, long resultID, long opcode, long[] operands) {
        addInstruction(new SpecConstantOpInstruction(resultType, resultID, opcode, operands));
    }

    public List<SpvInstruction> getInstructions() {
        return instructions;
    }

    public Module toModule() {
        return new Module(instructions, infoPool);
    }
}

package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.decorations.*;
import org.jglr.sbm.sampler.*;
import org.jglr.sbm.instructions.*;
import org.jglr.sbm.types.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.jglr.sbm.Opcodes.*;

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
    public void visitConstantTrue(long type, long resultID) {
        addInstruction(new BooleanConstantInstruction(type, resultID, true));
    }

    @Override
    public void visitConstantFalse(long type, long resultID) {
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
    public void visitImageTexelPointer(long resultType, long result, long image, long coordinate, long sample) {

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
        infoPool.registerType(resultID, new SampledImageType((ImageType) infoPool.getType(imageType)));
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
    public void visitReserveIdType(long resultID) {
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
    public void visitForwardPointerType(long type, StorageClass storageClass) {
        addInstruction(new ForwardPointerTypeInstruction(type, storageClass));
    }

    @Override
    public void visitNamedBarrierType(long resultID) {
        addInstruction(new NamedBarrierTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("namedBarrier"));
    }

    @Override
    public void visitPipeStorageType(long resultID) {
        addInstruction(new PipeStorageTypeInstruction(resultID));
        infoPool.registerType(resultID, new Type("pipeStorage"));
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
    public void visitLabel(long resultID) {
        addInstruction(new LabelInstruction(resultID));
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
    public void visitNop() {

    }

    @Override
    public void visitUndef(long resultType, long resultID) {
        addInstruction(new UndefInstruction(resultType, resultID));
    }

    @Override
    public void visitSizeOf(long resultType, long result, long pointer) {

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
        addInstruction(new KillInstruction());
    }

    @Override
    public void visitReturnValue(long valueID) {
        addInstruction(new ReturnValueInstruction(valueID));
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
    public void visitCompositeConstruct(long resultType, long resultID, long... constituents) {
        addInstruction(new CompositeConstructInstruction(resultType, resultID, constituents));
    }

    @Override
    public void visitSNegate(long resultTypeID, long resultID, long operandID) {
        addInstruction(new IntegerNegateInstruction(resultTypeID, resultID, operandID));
    }

    @Override
    public void visitFNegate(long resultTypeID, long resultID, long operandID) {
        addInstruction(new FloatNegateInstruction(resultTypeID, resultID, operandID));
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
    public void visitSpecConstantTrue(long resultType, long resultID) {
        addInstruction(new SpecConstantBoolInstruction(resultType, resultID, true));
    }

    @Override
    public void visitSpecConstantFalse(long resultType, long resultID) {
        addInstruction(new SpecConstantBoolInstruction(resultType, resultID, false));
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

    @Override
    public void visitSampledImage(long resultType, long result, long image, long sampler) {

    }

    @Override
    public void visitFunctionParameter(long resultType, long resultID) {
        addInstruction(new FunctionParameterInstruction(resultType, resultID));
    }

    public void visitIAdd(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpIAdd, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFAdd(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFAdd, resultTypeID, resultID, leftID, rightID));
    }

    public void visitISub(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpISub, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFSub(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFSub, resultTypeID, resultID, leftID, rightID));
    }

    public void visitIMul(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpIMul, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFMul(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFMul, resultTypeID, resultID, leftID, rightID));
    }

    public void visitUDiv(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpUDiv, resultTypeID, resultID, leftID, rightID));
    }

    public void visitSDiv(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpSDiv, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFDiv(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFDiv, resultTypeID, resultID, leftID, rightID));
    }

    public void visitUMod(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpUMod, resultTypeID, resultID, leftID, rightID));
    }

    public void visitSRem(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpSRem, resultTypeID, resultID, leftID, rightID));
    }

    public void visitSMod(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpSMod, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFRem(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFRem, resultTypeID, resultID, leftID, rightID));
    }

    public void visitFMod(long resultTypeID, long resultID, long leftID, long rightID) {
        addInstruction(new ArithmeticInstruction(OpFMod, resultTypeID, resultID, leftID, rightID));
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
        addInstruction(new CompositeExtractInstruction(resultTypeID, resultID, compositeID, indexes));
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


    public List<SpvInstruction> getInstructions() {
        return instructions;
    }

    public Module toModule() {
        return new Module(instructions, infoPool);
    }
}

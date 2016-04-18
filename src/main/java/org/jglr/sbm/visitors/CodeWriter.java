package org.jglr.sbm.visitors;

import org.jglr.flows.io.ByteArray;
import org.jglr.sbm.*;
import org.jglr.sbm.decorations.Decoration;
import org.jglr.sbm.image.*;

import java.io.UnsupportedEncodingException;
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
        newOpcode(OpSource, 2 + (filenameStringID == -1 ? 0 : 1 + sizeOf(sourceCode)));
        buffer.putUnsignedInt(language.ordinal());
        buffer.putUnsignedInt(version);
        if(filenameStringID >= 0) {
            buffer.putUnsignedInt(filenameStringID);
            if(sourceCode != null) {
                writeChars(sourceCode);
            }
        }
    }

    private void writeChars(String chars) {
        ByteOrder order = buffer.getByteOrder();
        buffer.setByteOrder(ByteOrder.LITTLE_ENDIAN);
        buffer.putChars(chars+'\0');
        buffer.setByteOrder(order);
    }

    private int sizeOf(String string) {
        return string == null ? 0 : (int) Math.ceil((string.length() + 1) / 4f);
    }

    @Override
    public void visitSourceContinued(String source) {
        newOpcode(OpSourceContinued, sizeOf(source));
        writeChars(source);
    }

    @Override
    public void visitSourceExtension(String source) {
        newOpcode(OpSourceExtension, sizeOf(source));
        writeChars(source);
    }

    @Override
    public void visitLine(long filenameID, long line, long column) {
        newOpcode(OpLine, 3);
        buffer.putUnsignedInt(filenameID);
        buffer.putUnsignedInt(line);
        buffer.putUnsignedInt(column);
    }

    @Override
    public void visitName(long target, String name) {
        newOpcode(OpName, 1 + sizeOf(name));
        buffer.putUnsignedInt(target);
        writeChars(name);
    }

    @Override
    public void visitMemberName(long structureType, long target, String name) {
        newOpcode(OpMemberName, 2 + sizeOf(name));
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(target);
        writeChars(name);
    }

    @Override
    public void visitString(long resultID, String value) {
        newOpcode(OpString, 1 + sizeOf(value));
        buffer.putUnsignedInt(resultID);
        writeChars(value);
    }

    @Override
    public void visitTrueConstant(long type, long resultID) {
        newOpcode(OpConstantTrue, 2);
        buffer.putUnsignedInt(type);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitFalseConstant(long type, long resultID) {
        newOpcode(OpConstantFalse, 2);
        buffer.putUnsignedInt(type);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitCapability(Capability cap) {
        newOpcode(OpCapability, 1);
        buffer.putUnsignedInt(cap.ordinal());
    }

    @Override
    public void visitExtendedInstructionSetImport(long resultID, String name) {
        newOpcode(OpExtInstImport, 1 + sizeOf(name));
        buffer.putUnsignedInt(resultID);
        writeChars(name);
    }

    @Override
    public void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands) {
        newOpcode(OpExtInst, 4 + operands.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(set);
        buffer.putUnsignedInt(instruction);
        for (long l : operands) {
            buffer.putUnsignedInt(l);
        }
    }

    @Override
    public void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel) {
        newOpcode(OpMemoryModel, 2);
        buffer.putUnsignedInt(addressingModel.ordinal());
        buffer.putUnsignedInt(memoryModel.ordinal());
    }

    @Override
    public void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces) {
        newOpcode(OpEntryPoint, 2 + sizeOf(name) + interfaces.length);
        buffer.putUnsignedInt(model.ordinal());
        buffer.putUnsignedInt(entryPoint);
        writeChars(name);
        for(long l : interfaces)
            buffer.putUnsignedInt(l);
    }

    @Override
    public void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer) {
        newOpcode(OpVariable, 3 + (initializer == -1 ? 0 : 1));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(storageClass.ordinal());
        if(initializer != -1)
            buffer.putUnsignedInt(initializer);
    }

    @Override
    public void visitConstant(long type, long resultID, long[] bitPattern) {
        newOpcode(OpConstant, 2 + bitPattern.length);
        buffer.putUnsignedInt(type);
        buffer.putUnsignedInt(resultID);
        for(long l : bitPattern) {
            buffer.putUnsignedInt(l);
        }
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
        newOpcode(OpFunctionEnd, 0);
    }

    @Override
    public void visitStore(long pointer, long object, MemoryAccess memoryAccess) {
        newOpcode(OpStore, 3);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(object);
        buffer.putUnsignedInt(memoryAccess.getMask());
    }

    @Override
    public void visitAccessChain(long resultType, long resultID, long base, long[] indexes) {
        newOpcode(OpAccessChain, 3 + indexes.length);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(base);
        for(long l : indexes)
            buffer.putUnsignedInt(l);
    }

    @Override
    public void visitLabel(long resultID) {
        newOpcode(OpLabel, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitExtension(String extension) {
        newOpcode(OpExtension, sizeOf(extension));
        writeChars(extension);
    }

    @Override
    public void visitReturn() {
        newOpcode(OpReturn, 0);
    }

    @Override
    public void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess) {
        newOpcode(OpLoad, 4);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(pointer);
        buffer.putUnsignedInt(memoryAccess.getMask());
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
        newOpcode(OpUndef, 2);
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitExecutionMode(long entryPoint, ExecutionMode mode) {
        newOpcode(OpExecutionMode, 2);
        buffer.putUnsignedInt(entryPoint);
        buffer.putUnsignedInt(mode.getType().ordinal());
    }

    @Override
    public void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands) {
        newOpcode(OpImageSampleImplicitLod, 4 + (operands.getMask() == 0 ? 0 : 1 + operands.getOperandCount()));
        buffer.putUnsignedInt(resultType);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(sampledImage);
        buffer.putUnsignedInt(coordinate);
        if(operands.getMask() != 0) {
            buffer.putUnsignedInt(operands.getMask());
            long[] operandValues = ImageOperands.mergeOperands(splitOperands);
            for (long o : operandValues)
                buffer.putUnsignedInt(o);
        }
    }

    @Override
    public void visitIntDecoration(Decoration decoration, long target, long value) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(decoration.ordinal());
        buffer.putUnsignedInt(value);
    }

    @Override
    public void visitFunctionParameterAttributeDecoration(long target, FunctionParameterAttribute attribute) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FuncParamAttr.ordinal());
        buffer.putUnsignedInt(attribute.ordinal());
    }

    @Override
    public void visitFPRoundingModeDecoration(long target, FPRoundingMode roundingMode) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FPRoundingMode.ordinal());
        buffer.putUnsignedInt(roundingMode.ordinal());
    }

    @Override
    public void visitFPFastMathModeDecoration(long target, FPFastMathMode fastMathMode) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.FPFastMathMode.ordinal());
        buffer.putUnsignedInt(fastMathMode.getMask());
    }

    @Override
    public void visitLinkageAttributesDecoration(long target, String name, LinkageType type) {
        newOpcode(OpDecorate, 3);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(Decoration.LinkageAttributes.ordinal());
        buffer.putUnsignedInt(type.ordinal());
    }

    @Override
    public void visitDecoration(long target, Decoration decoration) {
        newOpcode(OpDecorate, 2);
        buffer.putUnsignedInt(target);
        buffer.putUnsignedInt(decoration.ordinal());
    }

    @Override
    public void visitIntMemberDecoration(Decoration decoration, long structureType, long member, long value) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(decoration.ordinal());
        buffer.putUnsignedInt(value);
    }

    @Override
    public void visitFunctionParameterAttributeMemberDecoration(long structureType, long member, FunctionParameterAttribute attribute) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FuncParamAttr.ordinal());
        buffer.putUnsignedInt(attribute.ordinal());
    }

    @Override
    public void visitFPRoundingModeMemberDecoration(long structureType, long member, FPRoundingMode roundingMode) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FPRoundingMode.ordinal());
        buffer.putUnsignedInt(roundingMode.ordinal());
    }

    @Override
    public void visitFPFastMathModeMemberDecoration(long structureType, long member, FPFastMathMode mathMode) {
        newOpcode(OpMemberDecorate, 4);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.FPFastMathMode.ordinal());
        buffer.putUnsignedInt(mathMode.getMask());
    }

    @Override
    public void visitLinkageAttributesMemberDecoration(long structureType, long member, String name, LinkageType linkageType) {
        newOpcode(OpMemberDecorate, 4 + sizeOf(name));
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(Decoration.LinkageAttributes.ordinal());
        writeChars(name);
        buffer.putUnsignedInt(linkageType.ordinal());
    }

    @Override
    public void visitMemberDecoration(long structureType, long member, Decoration decoration) {
        newOpcode(OpMemberDecorate, 3);
        buffer.putUnsignedInt(structureType);
        buffer.putUnsignedInt(member);
        buffer.putUnsignedInt(decoration.ordinal());
    }

    @Override
    public void visitVoidType(long resultID) {
        newOpcode(OpTypeVoid, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitBoolType(long resultID) {
        newOpcode(OpTypeBool, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitFloatType(long resultID, long width) {
        newOpcode(OpTypeFloat, 2);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(width);
    }

    @Override
    public void visitIntType(long resultID, long width, boolean isSigned) {
        newOpcode(OpTypeInt, 3);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(width);
        buffer.putUnsignedInt(isSigned ? 1 : 0);
    }

    @Override
    public void visitVectorType(long resultID, long componentType, long componentCount) {
        newOpcode(OpTypeVec, 3);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(componentType);
        buffer.putUnsignedInt(componentCount);
    }

    @Override
    public void visitMatrixType(long resultID, long columnType, long columnCount) {
        newOpcode(OpTypeMatrix, 3);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(columnType);
        buffer.putUnsignedInt(columnCount);
    }

    @Override
    public void visitImageType(long resultID, long sampledType, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {
        newOpcode(OpTypeImage, 8 + (access == null ? 0 : 1));
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(sampledType);
        buffer.putUnsignedInt(dim.ordinal());
        buffer.putUnsignedInt(depth.ordinal());
        buffer.putUnsignedInt(arrayed ? 1 : 0);
        buffer.putUnsignedInt(multisampled ? 1 : 0);
        buffer.putUnsignedInt(sampling.ordinal());
        buffer.putUnsignedInt(format.ordinal());
        if(access != null)
            buffer.putUnsignedInt(access.ordinal());
    }

    @Override
    public void visitSamplerType(long resultID) {
        newOpcode(OpTypeSampler, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitSampledImageType(long resultID, long imageType) {
        newOpcode(OpTypeSampledImage, 2);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(imageType);
    }

    @Override
    public void visitArrayType(long resultID, long elementType, long length) {
        newOpcode(OpTypeArray, 3);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(elementType);
        buffer.putUnsignedInt(length);
    }

    @Override
    public void visitRuntimeArrayType(long resultID, long elementType) {
        newOpcode(OpTypeRuntimeArray, 2);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(elementType);
    }

    @Override
    public void visitStructType(long resultID, long[] memberTypes) {
        newOpcode(OpTypeStruct, 1 + memberTypes.length);
        buffer.putUnsignedInt(resultID);
        for (long l : memberTypes) {
            buffer.putUnsignedInt(l);
        }
    }

    @Override
    public void visitOpaqueType(long resultID, String name) {
        newOpcode(OpTypeOpaque, 1 + sizeOf(name));
        buffer.putUnsignedInt(resultID);
        writeChars(name);
    }

    @Override
    public void visitPointerType(long resultID, StorageClass storageClass, long type) {
        newOpcode(OpTypePointer, 3);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(storageClass.ordinal());
        buffer.putUnsignedInt(type);
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
        newOpcode(OpTypeEvent, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitDeviceEventType(long resultID) {
        newOpcode(OpTypeDeviceEvent, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitReserveIDType(long resultID) {
        newOpcode(OpTypeReservedID, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitQueueType(long resultID) {
        newOpcode(OpTypeQueue, 1);
        buffer.putUnsignedInt(resultID);
    }

    @Override
    public void visitPipeType(long resultID, AccessQualifier accessQualifier) {
        newOpcode(OpTypePipe, 2);
        buffer.putUnsignedInt(resultID);
        buffer.putUnsignedInt(accessQualifier.ordinal());
    }

    @Override
    public void visitForwardType(long type, StorageClass storageClass) {
        newOpcode(OpTypeForwardPointer, 2);
        buffer.putUnsignedInt(type);
        buffer.putUnsignedInt(storageClass.ordinal());
    }

    public byte[] toBytes() {
        return buffer.backingArray();
    }

    private void newOpcode(int opcode, int argCount) {
        buffer.putUnsignedInt(((long)(argCount+1) << 16L | opcode));
    }
}

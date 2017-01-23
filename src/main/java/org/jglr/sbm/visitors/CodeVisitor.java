package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.image.ImageOperands;

import java.util.Map;

public interface CodeVisitor extends TypeVisitor, DecorationVisitor {

    // source info
    void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode);
    void visitSourceContinued(String source);
    void visitSourceExtension(String source);
    void visitLine(long filenameID, long line, long column);

    void visitName(long target, String name);
    void visitMemberName(long structureType, long target, String name);

    void visitString(long resultID, String value);
    void visitTrueConstant(long type, long resultID);

    void visitFalseConstant(long type, long resultID);

    void visitCapability(Capability cap);
    void visitExtendedInstructionSetImport(long resultID, String name);

    void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands);

    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);

    void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces);

    void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer);

    void visitConstant(long type, long resultID, long[] bitPattern);

    void visitFunction(long resultType, long resultID, FunctionControl control, long funcType);

    void visitFunctionEnd();

    void visitStore(long pointer, long object, MemoryAccess memoryAccess);

    void visitAccessChain(long resultType, long resultID, long base, long[] indexes);

    void visitLabel(long resultID);

    void visitExtension(String extension);

    void visitReturn();

    void visitLoad(long resultType, long resultID, long pointer, MemoryAccess memoryAccess);

    void visitEnd();

    void reset();

    void visitUndef(long resultType, long resultID);

    void visitExecutionMode(long entryPoint, ExecutionMode mode);

    void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands);

    void visitKill();

    void visitReturnValue(long valueID);

    void visitNoLine();

    void visitFunctionCall(long resultType, long resultID, long functionID, long[] arguments);

    void visitCopyMemory(long targetID, long sourceID, MemoryAccess access);

    void visitCopyMemorySized(long targetID, long sourceID, long size, MemoryAccess access);

    void visitModuleProcessed(String process);
}

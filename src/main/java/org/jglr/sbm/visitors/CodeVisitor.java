package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.sampler.ImageOperands;
import org.jglr.sbm.sampler.SamplerAddressingMode;
import org.jglr.sbm.sampler.SamplerFilterMode;

import java.util.Map;

public interface CodeVisitor extends TypeVisitor, DecorationVisitor, ConstantVisitor, FunctionVisitor, MemoryVisitor {

    // source info
    void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode);

    void visitSourceContinued(String source);

    void visitSourceExtension(String source);

    void visitLine(long filenameID, long line, long column);

    void visitName(long target, String name);

    void visitMemberName(long structureType, long target, String name);

    void visitString(long resultID, String value);

    void visitCapability(Capability cap);

    void visitExtendedInstructionSetImport(long resultID, String name);

    void visitExecExtendedInstruction(long resultType, long resultID, long set, long instruction, long[] operands);

    void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces);

    void visitVariable(long resultType, long resultID, StorageClass storageClass, long initializer);

    void visitAccessChain(long resultType, long resultID, long base, long[] indexes);

    void visitLabel(long resultID);

    void visitExtension(String extension);

    void visitReturn();

    void visitEnd();

    void reset();

    void visitUndef(long resultType, long resultID);

    void visitExecutionMode(long entryPoint, ExecutionMode mode);

    void visitImageSampleImplicitLod(long resultType, long resultID, long sampledImage, long coordinate, ImageOperands operands, Map<Integer, long[]> splitOperands);

    void visitKill();

    void visitReturnValue(long valueID);

    void visitNoLine();

    void visitModuleProcessed(String process);
}

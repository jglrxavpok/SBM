package org.jglr.sbm.visitors;

import org.jglr.sbm.*;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;
import org.jglr.sbm.types.Type;
import org.jglr.sbm.visitors.SBMTypeVisitor;

public interface SBMCodeVisitor extends SBMTypeVisitor, SBMDecorationVisitor {

    // source info
    void visitSource(SourceLanguage language, long version, long filenameStringID, String sourceCode);
    void visitSourceContinued(String source);
    void visitSourceExtension(String source);
    void visitLine(long filenameID, long line, long column);

    void visitName(long target, String name);
    void visitMemberName(Type structureType, long target, String name);

    void visitString(long resultID, String value);
    void visitTrueConstant(long resultID);

    void visitFalseConstant(long resultID);

    void visitCapability(Capability cap);
    void visitExtendedInstructionSetImport(long resultID, String name);

    void visitExecExtendedInstruction(Type resultType, long resultID, long set, long instruction, long[] operands);

    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);

    void visitEntryPoint(ExecutionModel model, long entryPoint, String name, long[] interfaces);

    void visitVariable(Type resultType, long resultID, StorageClass storageClass, long initializer);

    void visitConstant(Type type, long resultID, long[] bitPattern);

    void visitFunction(Type resultType, long resultID, FunctionControl control, Type funcType);

    void visitFunctionEnd();
}

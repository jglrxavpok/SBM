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
    void visitSource(SourceLanguage language, int version, int filenameStringID, String sourceCode);
    void visitSourceContinued(String source);
    void visitSourceExtension(String source);
    void visitLine(int filenameID, int line, int column);

    void visitName(int target, String name);
    void visitMemberName(Type structureType, int target, String name);

    void visitString(int resultID, String value);
    void visitTrueConstant(int resultID);

    void visitFalseConstant(int resultID);

    void visitCapability(Capability cap);
    void visitExtendedInstructionSetImport(int resultID, String name);

    void visitExecExtendedInstruction(Type resultType, int resultID, int set, int instruction, int[] operands);

    void visitMemoryModel(AddressingModel addressingModel, MemoryModel memoryModel);

    void visitEntryPoint(ExecutionModel model, int entryPoint, String name, int[] interfaces);

    void visitVariable(Type resultType, int resultID, StorageClass storageClass, int initializer);

    void visitConstant(Type type, int resultID, int[] bitPattern);

    void visitFunction(Type resultType, int resultID, FunctionControl control, Type funcType);

    void visitFunctionEnd();
}

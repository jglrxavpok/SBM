package org.jglr.sbm.visitors;

public interface SBMHeaderVisitor {

    void visitSpirVersion(int value);

    void visitGeneratorMagicNumber(int value);

    void visitBound(int value);

    void visitInstructionSchema(int value);

}

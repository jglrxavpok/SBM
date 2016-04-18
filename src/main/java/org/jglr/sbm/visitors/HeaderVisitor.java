package org.jglr.sbm.visitors;

public interface HeaderVisitor {

    void visitSpirVersion(long value);

    void visitGeneratorMagicNumber(long value);

    void visitBound(long value);

    void visitInstructionSchema(long value);

}

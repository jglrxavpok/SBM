package org.jglr.sbm.visitors;

public class HeaderCollector implements SBMHeaderVisitor {
    private int generatorNumber;
    private int spirVersion;
    private int schema;
    private int bound;

    @Override
    public void visitSpirVersion(int value) {
        spirVersion = value;
    }

    @Override
    public void visitGeneratorMagicNumber(int value) {
        generatorNumber = value;
    }

    @Override
    public void visitBound(int value) {
        bound = value;
    }

    @Override
    public void visitInstructionSchema(int value) {
        schema = value;
    }

    public int getGeneratorNumber() {
        return generatorNumber;
    }

    public int getSpirVersion() {
        return spirVersion;
    }

    public int getBound() {
        return bound;
    }

    public int getSchema() {
        return schema;
    }
}

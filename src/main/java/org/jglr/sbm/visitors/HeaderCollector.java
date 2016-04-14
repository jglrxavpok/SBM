package org.jglr.sbm.visitors;

public class HeaderCollector implements SBMHeaderVisitor {
    private long generatorNumber;
    private long spirVersion;
    private long schema;
    private long bound;

    @Override
    public void visitSpirVersion(long value) {
        spirVersion = value;
    }

    @Override
    public void visitGeneratorMagicNumber(long value) {
        generatorNumber = value;
    }

    @Override
    public void visitBound(long value) {
        bound = value;
    }

    @Override
    public void visitInstructionSchema(long value) {
        schema = value;
    }

    public long getGeneratorNumber() {
        return generatorNumber;
    }

    public long getSpirVersion() {
        return spirVersion;
    }

    public long getBound() {
        return bound;
    }

    public long getSchema() {
        return schema;
    }
}

package org.jglr.sbm.types;

public class IntType extends Type {
    private final long width;
    private final boolean isSigned;

    public IntType(long width, boolean isSigned) {
        super((isSigned ? "" : "u")+"int"+width);
        this.width = width;
        this.isSigned = isSigned;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public long getWidth() {
        return width;
    }
}

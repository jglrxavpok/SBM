package org.jglr.sbm.types;

public class IntType extends Type {
    private final int width;
    private final boolean isSigned;

    public IntType(int width, boolean isSigned) {
        super((isSigned ? "" : "u")+"int"+width);
        this.width = width;
        this.isSigned = isSigned;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public int getWidth() {
        return width;
    }
}

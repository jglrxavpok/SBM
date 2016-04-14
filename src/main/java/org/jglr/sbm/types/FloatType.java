package org.jglr.sbm.types;

public class FloatType extends Type {
    private final long width;

    public FloatType(long width) {
        super("float"+width);
        this.width = width;
    }

    public long getWidth() {
        return width;
    }
}

package org.jglr.sbm.types;

public class ArrayType extends Type {
    private final Type elementType;
    private final long length;

    public ArrayType(Type elementType, long length) {
        super(elementType+"["+length+"]");
        this.elementType = elementType;
        this.length = length;
    }

    public Type getElementType() {
        return elementType;
    }

    public long getLength() {
        return length;
    }
}

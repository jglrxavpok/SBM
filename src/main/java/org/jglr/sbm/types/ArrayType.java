package org.jglr.sbm.types;

public class ArrayType extends Type {
    private final Type elementType;
    private final int length;

    public ArrayType(Type elementType, int length) {
        super(elementType+"["+length+"]");
        this.elementType = elementType;
        this.length = length;
    }

    public Type getElementType() {
        return elementType;
    }

    public int getLength() {
        return length;
    }
}

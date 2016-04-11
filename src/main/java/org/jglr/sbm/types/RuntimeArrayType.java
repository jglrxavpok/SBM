package org.jglr.sbm.types;

public class RuntimeArrayType extends Type {
    private final Type elementType;

    public RuntimeArrayType(Type elementType) {
        super(elementType+"[]");
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }
}

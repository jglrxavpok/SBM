package org.jglr.sbm.types;

public class VectorType extends Type {
    private final Type componentType;
    private final int componentCount;

    public VectorType(Type componentType, int componentCount) {
        super("vec"+componentCount+"("+componentType+")");
        this.componentType = componentType;
        this.componentCount = componentCount;
    }

    public int getComponentCount() {
        return componentCount;
    }

    public Type getComponentType() {
        return componentType;
    }
}

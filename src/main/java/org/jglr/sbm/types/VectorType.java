package org.jglr.sbm.types;

public class VectorType extends Type {
    private final Type componentType;
    private final long componentCount;

    public VectorType(Type componentType, long componentCount) {
        super("vec"+componentCount+"("+componentType+")");
        this.componentType = componentType;
        this.componentCount = componentCount;
    }

    public long getComponentCount() {
        return componentCount;
    }

    public Type getComponentType() {
        return componentType;
    }
}

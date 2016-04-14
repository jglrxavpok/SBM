package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

public class VectorTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long componentTypeID;
    private final long componentCount;
    private Type componentType;

    public VectorTypeInstruction(long resultID, long componentTypeID, long componentCount) {
        super(TYPE_VEC, 4, resultID);
        this.componentTypeID = componentTypeID;
        this.componentCount = componentCount;
    }

    public long getComponentCount() {
        return componentCount;
    }

    public long getComponentTypeID() {
        return componentTypeID;
    }

    public Type getComponentType() {
        return componentType;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        componentType = constantPool.getType(componentTypeID);
    }

    @Override
    public String toString() {
        return "TypeVec "+componentCount+" ("+nameOrID(componentTypeID, componentType)+")";
    }
}

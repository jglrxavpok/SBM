package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class VectorTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long componentTypeID;
    private final long componentCount;
    private Type componentType;

    public VectorTypeInstruction(long resultID, long componentTypeID, long componentCount) {
        super(OpTypeVector, 4, resultID);
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
    public void onVisitEnd(InfoPool infoPool) {
        componentType = infoPool.getType(componentTypeID);
    }

    @Override
    public String toString() {
        return "OpTypeVec "+componentCount+" ("+nameOrID(componentTypeID, componentType)+")";
    }
}

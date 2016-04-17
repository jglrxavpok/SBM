package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class RuntimeArrayTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long elementTypeID;
    private Type elementType;

    public RuntimeArrayTypeInstruction(long resultID, long elementTypeID) {
        super(TypeRuntimeArray, 3, resultID);
        this.elementTypeID = elementTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        elementType = infoPool.getType(elementTypeID);
    }

    @Override
    public String toString() {
        return "TypeRuntimeArray "+nameOrID(elementTypeID, elementType);
    }
}

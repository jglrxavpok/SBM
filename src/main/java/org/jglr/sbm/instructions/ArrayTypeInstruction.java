package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class ArrayTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long elementTypeID;
    private final long length;
    private Type elementType;

    public ArrayTypeInstruction(long resultID, long elementTypeID, long length) {
        super(OpTypeArray, 4, resultID);
        this.elementTypeID = elementTypeID;
        this.length = length;
    }

    public long getElementTypeID() {
        return elementTypeID;
    }

    public long getLength() {
        return length;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        elementType = infoPool.getType(elementTypeID);
    }

    @Override
    public String toString() {
        return "OpTypeArray "+nameOrID(elementTypeID, elementType)+"["+length+"]";
    }
}

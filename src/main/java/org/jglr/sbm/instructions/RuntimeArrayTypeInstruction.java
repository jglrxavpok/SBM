package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

public class RuntimeArrayTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long elementTypeID;
    private Type elementType;

    public RuntimeArrayTypeInstruction(long resultID, long elementTypeID) {
        super(TYPE_RUNTIME_ARRAY, 3, resultID);
        this.elementTypeID = elementTypeID;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        elementType = constantPool.getType(elementTypeID);
    }

    @Override
    public String toString() {
        return "TypeRuntimeArray "+nameOrID(elementTypeID, elementType);
    }
}

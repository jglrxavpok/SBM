package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class BooleanConstantInstruction extends ResultInstruction implements ResolvableInstruction {
    private final boolean value;
    private final long typeID;
    private Type type;

    public BooleanConstantInstruction(long typeID, long resultID, boolean value) {
        super(value ? ConstantTrue : ConstantFalse, 2, resultID);
        this.typeID = typeID;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public long getTypeID() {
        return typeID;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BooleanConstantInstruction "+value;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        type = infoPool.getType(typeID);
        if(!type.getName().equals("bool")) {
            throw new IllegalArgumentException("Invalid type for boolean constant: "+type.getName());
        }
    }
}

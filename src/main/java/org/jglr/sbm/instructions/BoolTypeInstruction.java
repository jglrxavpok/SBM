package org.jglr.sbm.instructions;

public class BoolTypeInstruction extends ResultInstruction {
    public BoolTypeInstruction(long resultID) {
        super(TYPE_BOOL, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeBoolean";
    }
}

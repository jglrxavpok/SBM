package org.jglr.sbm.instructions;

public class VoidTypeInstruction extends ResultInstruction {
    public VoidTypeInstruction(long resultID) {
        super(TypeVoid, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeVoid";
    }
}

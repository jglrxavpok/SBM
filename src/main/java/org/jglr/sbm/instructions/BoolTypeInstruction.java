package org.jglr.sbm.instructions;

public class BoolTypeInstruction extends ResultInstruction {
    public BoolTypeInstruction(long resultID) {
        super(OpTypeBool, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeBool";
    }
}

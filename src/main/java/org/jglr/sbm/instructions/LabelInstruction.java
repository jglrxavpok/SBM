package org.jglr.sbm.instructions;

public class LabelInstruction extends ResultInstruction {

    public LabelInstruction(long resultID) {
        super(OpLabel, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpLabel "+getResultID();
    }
}

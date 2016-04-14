package org.jglr.sbm.instructions;

public class LabelInstruction extends ResultInstruction {

    public LabelInstruction(long resultID) {
        super(LABEL, 2, resultID);
    }

    @Override
    public String toString() {
        return "Label "+getResultID();
    }
}

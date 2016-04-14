package org.jglr.sbm.instructions;

public class ReturnInstruction extends SpvInstruction {
    public ReturnInstruction() {
        super(RETURN, 1);
    }

    @Override
    public String toString() {
        return "Return";
    }
}

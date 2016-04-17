package org.jglr.sbm.instructions;

public class ReturnInstruction extends SpvInstruction {
    public ReturnInstruction() {
        super(Return, 1);
    }

    @Override
    public String toString() {
        return "Return";
    }
}

package org.jglr.sbm.instructions;

public class ReturnInstruction extends SpvInstruction {
    public ReturnInstruction() {
        super(OpReturn, 1);
    }

    @Override
    public String toString() {
        return "OpReturn";
    }
}

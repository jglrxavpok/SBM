package org.jglr.sbm.instructions;

public class NoLineInstruction extends SpvInstruction {
    public NoLineInstruction() {
        super(OpNoLine, 1);
    }

    @Override
    public String toString() {
        return "OpNoLine";
    }
}

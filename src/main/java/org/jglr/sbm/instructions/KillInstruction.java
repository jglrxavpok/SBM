package org.jglr.sbm.instructions;

public class KillInstruction extends SpvInstruction {
    public KillInstruction() {
        super(OpKill, 1);
    }

    @Override
    public String toString() {
        return "OpKill";
    }
}

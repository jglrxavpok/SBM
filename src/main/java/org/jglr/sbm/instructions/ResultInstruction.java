package org.jglr.sbm.instructions;

public abstract class ResultInstruction extends SpvInstruction {

    private final long resultID;

    public ResultInstruction(int opcode, int wordCount, long resultID) {
        super(opcode, wordCount);
        this.resultID = resultID;
    }

    public long getResultID() {
        return resultID;
    }

}

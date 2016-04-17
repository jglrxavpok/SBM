package org.jglr.sbm.instructions;

public class IntTypeInstruction extends ResultInstruction {
    private final long width;
    private final boolean isSigned;

    public IntTypeInstruction(long resultID, long width, boolean isSigned) {
        super(OpTypeInt, 4, resultID);
        this.width = width;
        this.isSigned = isSigned;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public long getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "OpTypeInt "+width+(isSigned ? " Unsigned" : "");
    }
}

package org.jglr.sbm.instructions;

public class FloatTypeInstruction extends ResultInstruction {
    private final long width;

    public FloatTypeInstruction(long resultID, long width) {
        super(TypeFloat, 3, resultID);
        this.width = width;
    }

    public long getWidth() {
        return width;
    }

    @Override
    public String toString() {
        return "TypeFloat "+width;
    }
}

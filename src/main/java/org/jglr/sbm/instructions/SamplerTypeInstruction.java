package org.jglr.sbm.instructions;

public class SamplerTypeInstruction extends ResultInstruction {
    public SamplerTypeInstruction(long resultID) {
        super(OpTypeSampler, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeSampler";
    }
}

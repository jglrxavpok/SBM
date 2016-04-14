package org.jglr.sbm.instructions;

public class SamplerTypeInstruction extends ResultInstruction {
    public SamplerTypeInstruction(long resultID) {
        super(TYPE_SAMPLER, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeSampler";
    }
}

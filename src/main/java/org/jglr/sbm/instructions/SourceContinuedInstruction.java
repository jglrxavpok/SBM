package org.jglr.sbm.instructions;

public class SourceContinuedInstruction extends SpvInstruction {
    private final String source;

    public SourceContinuedInstruction(String source) {
        super(SOURCE_CONTINUED, getWordCount(source)+1);
        this.source = source;
    }

    @Override
    public String toString() {
        return "Source continued: "+source;
    }
}

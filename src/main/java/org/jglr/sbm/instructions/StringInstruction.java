package org.jglr.sbm.instructions;

public class StringInstruction extends ResultInstruction {
    private final String value;

    public StringInstruction(long resultID, String value) {
        super(STRING, getWordCount(value)+1, resultID);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "String "+value;
    }
}

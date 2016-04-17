package org.jglr.sbm.instructions;

public class OpaqueTypeInstruction extends ResultInstruction {
    private final String name;

    public OpaqueTypeInstruction(long resultID, String name) {
        super(TYPE_OPAQUE, 2 + getWordCount(name), resultID);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "TypeOpaque "+name;
    }
}

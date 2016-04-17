package org.jglr.sbm.instructions;

public class NameInstruction extends SpvInstruction {
    private final long target;
    private final String name;

    public NameInstruction(long target, String name) {
        super(Name, 2 + getWordCount(name));
        this.target = target;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "Name for "+target+": "+name;
    }
}

package org.jglr.sbm.instructions;

public class EventTypeInstruction extends ResultInstruction {
    public EventTypeInstruction(long resultID) {
        super(TypeEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeEvent";
    }
}

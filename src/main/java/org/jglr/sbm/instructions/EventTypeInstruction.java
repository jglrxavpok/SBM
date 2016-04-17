package org.jglr.sbm.instructions;

public class EventTypeInstruction extends ResultInstruction {
    public EventTypeInstruction(long resultID) {
        super(OpTypeEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeEvent";
    }
}

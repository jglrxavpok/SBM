package org.jglr.sbm.instructions;

public class QueueTypeInstruction extends ResultInstruction {
    public QueueTypeInstruction(long resultID) {
        super(TypeDeviceEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeQueue";
    }
}

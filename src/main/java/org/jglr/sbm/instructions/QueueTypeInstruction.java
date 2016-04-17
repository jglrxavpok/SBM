package org.jglr.sbm.instructions;

public class QueueTypeInstruction extends ResultInstruction {
    public QueueTypeInstruction(long resultID) {
        super(OpTypeDeviceEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeQueue";
    }
}

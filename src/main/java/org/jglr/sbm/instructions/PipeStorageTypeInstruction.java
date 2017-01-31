package org.jglr.sbm.instructions;

public class PipeStorageTypeInstruction extends ResultInstruction {
    public PipeStorageTypeInstruction(long resultID) {
        super(OpTypeDeviceEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypePipeStorage";
    }
}

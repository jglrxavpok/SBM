package org.jglr.sbm.instructions;

public class DeviceEventTypeInstruction extends ResultInstruction {
    public DeviceEventTypeInstruction(long resultID) {
        super(OpTypeDeviceEvent, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeDeviceEvent";
    }
}

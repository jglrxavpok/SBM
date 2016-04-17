package org.jglr.sbm.instructions;

public class DeviceEventTypeInstruction extends ResultInstruction {
    public DeviceEventTypeInstruction(long resultID) {
        super(TYPE_DEVICE_EVENT, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeDeviceEvent";
    }
}

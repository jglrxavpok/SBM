package org.jglr.sbm.instructions;

public class ReserveIDTypeInstruction extends ResultInstruction {
    public ReserveIDTypeInstruction(long resultID) {
        super(TYPE_DEVICE_EVENT, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeReserveID";
    }
}

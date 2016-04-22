package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;

public class ReturnValueInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long valueID;
    private String valueName;

    public ReturnValueInstruction(long valueID) {
        super(OpReturnValue, 2);
        this.valueID = valueID;
    }

    public String getValueName() {
        return valueName;
    }

    public long getValueID() {
        return valueID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        valueName = infoPool.getName(valueID);
    }

    @Override
    public String toString() {
        return "OpReturnValue "+nameOrID(valueID, valueName);
    }
}

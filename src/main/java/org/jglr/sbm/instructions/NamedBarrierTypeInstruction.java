package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;

public class NamedBarrierTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private String name;

    public NamedBarrierTypeInstruction(long resultID) {
        super(OpTypeNamedBarrier, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpTypeNamedBarrier "+nameOrID(getResultID(), name);
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
    }
}

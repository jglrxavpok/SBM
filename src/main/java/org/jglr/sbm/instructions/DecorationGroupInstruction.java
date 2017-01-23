package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;

public class DecorationGroupInstruction extends ResultInstruction implements ResolvableInstruction {
    private String name;

    public DecorationGroupInstruction(long resultID) {
        super(OpDecorationGroup, 2, resultID);
    }

    @Override
    public String toString() {
        return "OpDecorationGroup "+nameOrID(getResultID(), name);
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
    }
}

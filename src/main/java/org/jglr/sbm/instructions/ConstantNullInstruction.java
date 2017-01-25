package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class ConstantNullInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private String name;
    private Type resultType;

    public ConstantNullInstruction(long resultTypeID, long resultID) {
        super(OpConstantNull, 3, resultID);
        this.resultTypeID = resultTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(getResultID());
    }

    @Override
    public String toString() {
        return "OpConstantNull "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType);
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class UndefInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private Type resultType;

    public UndefInstruction(long resultTypeID, long resultID) {
        super(UNDEF, 3, resultID);
        this.resultTypeID = resultTypeID;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public Type getResultType() {
        return resultType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "Undef "+nameOrID(resultTypeID, resultType);
    }
}

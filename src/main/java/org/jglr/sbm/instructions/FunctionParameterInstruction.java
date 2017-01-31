package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class FunctionParameterInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private Type resultType;
    private String name;

    public FunctionParameterInstruction(long resultTypeID, long resultID) {
        super(OpFunctionParameter, 3, resultID);
        this.resultTypeID = resultTypeID;
    }

    public long getResultType() {
        return resultTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(getResultID());
    }

    @Override
    public String toString() {
        return "OpFunctionParameter "+nameOrID(getResultID(),name)+" of type "+nameOrID(resultTypeID, resultType);
    }
}

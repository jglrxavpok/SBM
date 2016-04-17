package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class FunctionTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long returnTypeID;
    private final long[] parameterTypeIDs;
    private Type[] parameterTypes;
    private Type returnType;

    public FunctionTypeInstruction(long resultID, long returnTypeID, long[] parameterTypeIDs) {
        super(TypeFunction, 3 + parameterTypeIDs.length, resultID);
        this.returnTypeID = returnTypeID;
        this.parameterTypeIDs = parameterTypeIDs;
    }

    public long[] getParameterTypeIDs() {
        return parameterTypeIDs;
    }

    public long getReturnTypeID() {
        return returnTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        parameterTypes = infoPool.getTypes(parameterTypeIDs);
        returnType = infoPool.getType(returnTypeID);
    }

    @Override
    public String toString() {
        String params = "";
        for (int i = 0; i < parameterTypes.length; i++) {
            if(i != 0)
                params += ", ";
            params += nameOrID(parameterTypeIDs[i], parameterTypes[i]);
        }
        return "TypeFunction ("+params+")"+nameOrID(returnTypeID, returnType);
    }
}

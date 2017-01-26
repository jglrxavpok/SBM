package org.jglr.sbm.instructions;


import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class SpecConstantInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long[] value;
    private Type resultType;
    private String name;

    public SpecConstantInstruction(long resultType, long resultID, long[] value) {
        super(OpSpecConstant, 3+value.length, resultID);
        this.resultTypeID = resultType;
        this.value = value;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long[] getValue() {
        return value;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(getResultID());
    }

    @Override
    public String toString() {
        return "OpSpecConstant "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType) +" = "+Arrays.toString(value);
    }
}

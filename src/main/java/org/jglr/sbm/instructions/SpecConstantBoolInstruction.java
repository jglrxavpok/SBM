package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.Opcodes;
import org.jglr.sbm.types.Type;

public class SpecConstantBoolInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final boolean defaultValue;
    private Type resultType;

    public SpecConstantBoolInstruction(long resultType, long resultID, boolean defaultValue) {
        super(defaultValue ? OpSpecConstantTrue : OpSpecConstantFalse, 4, resultID);
        this.resultTypeID = resultType;
        this.defaultValue = defaultValue;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public boolean isDefaultValue() {
        return defaultValue;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return Opcodes.getName(getOpcode())+" "+nameOrID(resultTypeID, resultType)+" = "+defaultValue;
    }
}

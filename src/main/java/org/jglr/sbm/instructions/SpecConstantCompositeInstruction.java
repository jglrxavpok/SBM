package org.jglr.sbm.instructions;


import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class SpecConstantCompositeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long[] constituents;
    private Type resultType;
    private String name;

    public SpecConstantCompositeInstruction(long resultType, long resultID, long[] constituents) {
        super(OpSpecConstant, 3+constituents.length, resultID);
        this.resultTypeID = resultType;
        this.constituents = constituents;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long[] getConstituents() {
        return constituents;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(getResultID());
    }

    @Override
    public String toString() {
        return "OpSpecConstantComposite "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType) +"; Constituents: "+Arrays.toString(constituents);
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class CompositeConstructInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long[] constituents;
    private String name;
    private String[] constituentNames;
    private Type resultType;

    public CompositeConstructInstruction(long resultTypeID, long resultID, long[] constituents) {
        super(OpCompositeConstruct, 3 + constituents.length, resultID);
        this.resultTypeID = resultTypeID;
        this.constituents = constituents;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long[] getConstituents() {
        return constituents;
    }

    public String getName() {
        return name;
    }

    public String[] getConstituentNames() {
        return constituentNames;
    }

    public Type getResultType() {
        return resultType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        constituentNames = infoPool.getNames(constituents);
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(getResultID());
    }

    @Override
    public String toString() {
        return "OpCompositeConstruct "+nameOrID(resultTypeID, resultType)+"("+String.join(", ", namesOrID(constituents, constituentNames))+")";
    }
}

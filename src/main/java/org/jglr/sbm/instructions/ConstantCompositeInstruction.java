package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class ConstantCompositeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long[] constituents;
    private Type resultType;
    private String name;
    private String[] constituentNames;

    public ConstantCompositeInstruction(long resultType, long resultID, long[] constituents) {
        super(OpConstantComposite, 3 + constituents.length, resultID);
        this.resultTypeID = resultType;
        this.constituents = constituents;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        name = infoPool.getName(resultTypeID);
        constituentNames = infoPool.getNames(constituents);
    }

    @Override
    public String toString() {
        return "OpConstantComposite "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType)+" ["+String.join(", ", constituentNames)+"]";
    }
}

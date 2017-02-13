package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class CompositeExtractInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long baseID;
    private final long[] indexIDs;
    private String baseName;
    private Type resultType;

    public CompositeExtractInstruction(long resultTypeID, long resultID, long baseID, long[] indexIDs) {
        super(OpCompositeExtract, 4 + indexIDs.length, resultID);
        this.resultTypeID = resultTypeID;
        this.baseID = baseID;
        this.indexIDs = indexIDs;
    }

    public long getBaseID() {
        return baseID;
    }

    public long[] getIndexIDs() {
        return indexIDs;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public String getBaseName() {
        return baseName;
    }

    public Type getResultType() {
        return resultType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        baseName = infoPool.getName(baseID);
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "OpCompositeExtract "+nameOrID(baseID, baseName)+" indexes: "+Arrays.toString(indexIDs)+" (result: "+nameOrID(resultTypeID, resultType)+")";
    }
}


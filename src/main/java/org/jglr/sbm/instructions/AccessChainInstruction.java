package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class AccessChainInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long baseID;
    private final long[] indexIDs;
    private String baseName;
    private Type resultType;
    private String[] indexNames;

    public AccessChainInstruction(long resultTypeID, long resultID, long baseID, long[] indexIDs) {
        super(AccessChain, 4 + indexIDs.length, resultID);
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

    public String[] getIndexNames() {
        return indexNames;
    }

    public Type getResultType() {
        return resultType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        baseName = infoPool.getName(baseID);
        indexNames = infoPool.getNames(indexIDs);
        resultType = infoPool.getType(resultTypeID);
    }

    @Override
    public String toString() {
        return "AccessChain "+nameOrID(baseID, baseName)+" indexes: "+(indexNames != null ? Arrays.toString(indexNames) : Arrays.toString(indexIDs))+" (result: "+nameOrID(resultTypeID, resultType)+")";
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.MemoryAccess;

public class CopyMemoryInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long sourceID;
    private final long targetID;
    private final MemoryAccess access;
    private String sourceName;
    private String targetName;

    public CopyMemoryInstruction(long sourceID, long targetID, MemoryAccess access) {
        super(OpCopyMemory, 3);
        this.sourceID = sourceID;
        this.targetID = targetID;
        this.access = access;
    }

    public long getSourceID() {
        return sourceID;
    }

    public long getTargetID() {
        return targetID;
    }

    public MemoryAccess getAccess() {
        return access;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        sourceName = infoPool.getName(sourceID);
        targetName = infoPool.getName(targetID);
    }

    @Override
    public String toString() {
        return "OpCopyMemory["+access.getMask()+"] ( "+nameOrID(sourceID, sourceName)+" -> "+nameOrID(targetID, targetName)+")";
    }
}

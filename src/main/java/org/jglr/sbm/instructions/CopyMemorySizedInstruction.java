package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.MemoryAccess;

public class CopyMemorySizedInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long sourceID;
    private final long targetID;
    private final MemoryAccess access;
    private final long size;
    private String sourceName;
    private String targetName;

    public CopyMemorySizedInstruction(long sourceID, long targetID, long size, MemoryAccess access) {
        super(OpCopyMemorySized, 4);
        this.sourceID = sourceID;
        this.targetID = targetID;
        this.access = access;
        this.size = size;
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

    public long getSize() {
        return size;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        sourceName = infoPool.getName(sourceID);
        targetName = infoPool.getName(targetID);
    }

    @Override
    public String toString() {
        return "OpCopyMemorySized["+access.getMask()+"] ( "+nameOrID(sourceID, sourceName)+" -> "+nameOrID(targetID, targetName)+"), "+size+" bytes";
    }
}

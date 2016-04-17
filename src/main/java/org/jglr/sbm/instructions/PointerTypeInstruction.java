package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.types.Type;

public class PointerTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final StorageClass storageClass;
    private final long typeID;
    private Type type;

    public PointerTypeInstruction(long resultID, StorageClass storageClass, long typeID) {
        super(OpTypePointer, 4, resultID);
        this.storageClass = storageClass;
        this.typeID = typeID;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        type = infoPool.getType(typeID);
    }

    @Override
    public String toString() {
        return "OpTypePointer "+nameOrID(typeID, type)+" "+storageClass.name();
    }
}

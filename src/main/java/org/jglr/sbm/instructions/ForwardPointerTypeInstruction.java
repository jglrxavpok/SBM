package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.types.Type;

public class ForwardPointerTypeInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long typeID;
    private final StorageClass storageClass;
    private Type type;

    public ForwardPointerTypeInstruction(long typeID, StorageClass storageClass) {
        super(TypeForwardPointer, 3);
        this.typeID = typeID;
        this.storageClass = storageClass;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public long getTypeID() {
        return typeID;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TypeForwardPointer "+nameOrID(typeID, type)+" ("+storageClass.name()+")";
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        type = infoPool.getType(typeID);
    }
}

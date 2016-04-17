package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.StorageClass;
import org.jglr.sbm.types.Type;

public class ForwardPointerTypeInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long typeID;
    private final StorageClass storageClass;
    private Type type;

    public ForwardPointerTypeInstruction(long typeID, StorageClass storageClass) {
        super(TYPE_FORWARD_POINTER, 3);
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
    public void onVisitEnd(ConstantPool constantPool) {
        type = constantPool.getType(typeID);
    }
}

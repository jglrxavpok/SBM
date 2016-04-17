package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.MemoryAccess;

public class StoreInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long pointer;
    private final long object;
    private final MemoryAccess memoryAccess;
    private String objectName;
    private String pointerName;

    public StoreInstruction(long pointer, long object, MemoryAccess memoryAccess) {
        super(OpStore, 3 + (memoryAccess == null ? 0 : 1));
        this.pointer = pointer;
        this.object = object;
        this.memoryAccess = memoryAccess;
    }

    public MemoryAccess getMemoryAccess() {
        return memoryAccess;
    }

    public long getObject() {
        return object;
    }

    public long getPointer() {
        return pointer;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        objectName = infoPool.getName(object);
        pointerName = infoPool.getName(pointer);
    }

    @Override
    public String toString() {
        String access = "";
        if(memoryAccess != null) {
            access = " (addressing: 0x"+Long.toHexString(memoryAccess.getMask())+")";
        }
        return "OpStore "+nameOrID(object, objectName)+" to pointer "+nameOrID(pointer, pointerName)+access;
    }
}

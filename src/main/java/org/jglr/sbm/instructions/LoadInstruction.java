package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.MemoryAccess;
import org.jglr.sbm.types.Type;

public class LoadInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long pointer;
    private final MemoryAccess memoryAccess;
    private Type resultType;
    private String pointerName;

    public LoadInstruction(long resultTypeID, long resultID, long pointer, MemoryAccess memoryAccess) {
        super(LOAD, 4 + (memoryAccess == null ? 0 : 1), resultID);
        this.resultTypeID = resultTypeID;
        this.pointer = pointer;
        this.memoryAccess = memoryAccess;
    }

    public MemoryAccess getMemoryAccess() {
        return memoryAccess;
    }

    public long getPointer() {
        return pointer;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public Type getResultType() {
        return resultType;
    }

    public String getPointerName() {
        return pointerName;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        resultType = constantPool.getType(resultTypeID);
        pointerName = constantPool.getName(pointer);
    }

    @Override
    public String toString() {
        String access = "";
        if(memoryAccess != null) {
            access = " (addressing: 0x"+Long.toHexString(memoryAccess.getMask())+")";
        }
        return "Load "+nameOrID(resultTypeID, resultType)+" from pointer "+nameOrID(pointer, pointerName)+access;
    }
}

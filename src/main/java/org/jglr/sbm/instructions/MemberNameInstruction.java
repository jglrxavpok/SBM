package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

public class MemberNameInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long structureTypeID;
    private final long target;
    private final String name;
    private Type structureType;

    public MemberNameInstruction(long structureTypeID, long target, String name) {
        super(NAME, 3 + getWordCount(name));
        this.structureTypeID = structureTypeID;
        this.target = target;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTarget() {
        return target;
    }

    @Override
    public String toString() {
        return "MemberName for "+target+" inside"+nameOrID(structureTypeID, structureType)+": "+name;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        structureType = constantPool.getType(structureTypeID);
    }
}

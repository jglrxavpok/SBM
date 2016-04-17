package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class StructTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long[] memberTypeIDs;
    private Type[] memberTypes;

    public StructTypeInstruction(long resultID, long[] memberTypeIDs) {
        super(TYPE_STRUCT, 2 + memberTypeIDs.length, resultID);
        this.memberTypeIDs = memberTypeIDs;
    }

    public long[] getMemberTypeIDs() {
        return memberTypeIDs;
    }

    public Type[] getMemberTypes() {
        return memberTypes;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        memberTypes = constantPool.getTypes(memberTypeIDs);
    }

    @Override
    public String toString() {
        return "TypeStruct "+ Arrays.toString(namesOrID(memberTypeIDs, memberTypes));
    }
}

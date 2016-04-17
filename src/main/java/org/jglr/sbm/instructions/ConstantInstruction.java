package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class ConstantInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long typeID;
    private final long[] bitPattern;
    private Type type;

    public ConstantInstruction(long typeID, long resultID, long[] bitPattern) {
        super(CONSTANT, 2 + bitPattern.length, resultID);
        this.typeID = typeID;
        this.bitPattern = bitPattern;
    }

    public long[] getBitPattern() {
        return bitPattern;
    }

    public long getTypeID() {
        return typeID;
    }

    public Type getType() {
        return type;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        type = infoPool.getType(typeID);
    }

    @Override
    public String toString() {
        return "ConstantDeclaration "+nameOrID(typeID, type)+" = "+ Arrays.toString(bitPattern);
    }
}

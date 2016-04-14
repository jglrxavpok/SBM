package org.jglr.sbm.instructions;

import org.jglr.sbm.types.Type;

public class VoidTypeInstruction extends ResultInstruction {
    public VoidTypeInstruction(long resultID) {
        super(TYPE_VOID, 2, resultID);
    }

    @Override
    public String toString() {
        return "TypeVoid";
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.BuiltIn;
import org.jglr.sbm.Decoration;

public class IntDecorationInstruction extends DecorationInstruction {
    private final long value;

    public IntDecorationInstruction(Decoration decoration, long target, long value) {
        super(4, decoration, target);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        String value = "with value 0x"+Long.toHexString(getValue());
        if(getDecoration() == Decoration.BuiltIn) {
            value = BuiltIn.values()[(int) getValue()].name();
        }
        return "Decorate "+nameOrID(getTarget(), getTargetName())+" with "+getDecoration().name()+" "+value;
    }
}

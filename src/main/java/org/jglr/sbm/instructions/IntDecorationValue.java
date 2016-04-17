package org.jglr.sbm.instructions;

import org.jglr.sbm.BuiltIn;
import org.jglr.sbm.Decoration;
import org.jglr.sbm.decorations.DecorationValue;

public class IntDecorationValue extends DecorationValue {
    private final long value;

    public IntDecorationValue(Decoration decoration, long value) {
        super(decoration);
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        String valueName;
        if(getType() == Decoration.BuiltIn) {
            valueName = BuiltIn.values()[(int) value].name();
        } else {
            valueName = "0x"+Long.toHexString(value);
        }
        return super.toString()+" "+valueName;
    }
}

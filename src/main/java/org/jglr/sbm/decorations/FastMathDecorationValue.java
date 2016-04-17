package org.jglr.sbm.decorations;

import org.jglr.sbm.Decoration;
import org.jglr.sbm.FPFastMathMode;

public class FastMathDecorationValue extends DecorationValue {
    private final FPFastMathMode fastMathMode;

    public FastMathDecorationValue(FPFastMathMode fastMathMode) {
        super(Decoration.FPFastMathMode);
        this.fastMathMode = fastMathMode;
    }

    public FPFastMathMode getFastMathMode() {
        return fastMathMode;
    }

    @Override
    public String toString() {
        return super.toString()+" "+Long.toHexString(fastMathMode.getMask());
    }
}

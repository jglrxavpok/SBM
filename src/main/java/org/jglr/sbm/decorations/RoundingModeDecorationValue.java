package org.jglr.sbm.decorations;

import org.jglr.sbm.FPRoundingMode;

public class RoundingModeDecorationValue extends DecorationValue {
    private final FPRoundingMode roundingMode;

    public RoundingModeDecorationValue(FPRoundingMode roundingMode) {
        super(Decoration.FPRoundingMode);
        this.roundingMode = roundingMode;
    }

    public FPRoundingMode getRoundingMode() {
        return roundingMode;
    }

    @Override
    public String toString() {
        return super.toString()+" "+roundingMode.name();
    }
}

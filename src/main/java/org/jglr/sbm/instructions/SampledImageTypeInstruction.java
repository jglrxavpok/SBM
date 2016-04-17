package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class SampledImageTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long imageTypeID;
    private Type imageType;

    public SampledImageTypeInstruction(long resultID, long imageTypeID) {
        super(OpTypeSampledImage, 3, resultID);
        this.imageTypeID = imageTypeID;
    }

    public Type getImageType() {
        return imageType;
    }

    public long getImageTypeID() {
        return imageTypeID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        imageType = infoPool.getType(imageTypeID);
    }

    @Override
    public String toString() {
        return "OpTypeSampledImage "+nameOrID(imageTypeID, imageType);
    }
}

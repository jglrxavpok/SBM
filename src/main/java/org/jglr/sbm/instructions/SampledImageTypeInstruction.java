package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

public class SampledImageTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long imageTypeID;
    private Type imageType;

    public SampledImageTypeInstruction(long resultID, long imageTypeID) {
        super(TYPE_SAMPLED_IMAGE, 3, resultID);
        this.imageTypeID = imageTypeID;
    }

    public Type getImageType() {
        return imageType;
    }

    public long getImageTypeID() {
        return imageTypeID;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        imageType = constantPool.getType(imageTypeID);
    }

    @Override
    public String toString() {
        return "TypeSampledImage "+nameOrID(imageTypeID, imageType);
    }
}

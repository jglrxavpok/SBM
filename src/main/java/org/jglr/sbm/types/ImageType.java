package org.jglr.sbm.types;

import org.jglr.sbm.AccessQualifier;
import org.jglr.sbm.image.Dimensionality;
import org.jglr.sbm.image.ImageDepth;
import org.jglr.sbm.image.ImageFormat;
import org.jglr.sbm.image.Sampling;

public class ImageType extends Type {
    private final Type sampledType;
    private final Dimensionality dimensionality;
    private final ImageDepth depth;
    private final boolean arrayed;
    private final boolean multisampled;
    private final Sampling sampling;
    private final ImageFormat format;
    private final AccessQualifier qualifier;

    public ImageType(Type sampledType, Dimensionality dimensionality, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier qualifier) {
        super("image("+sampledType+")");
        this.sampledType = sampledType;
        this.dimensionality = dimensionality;
        this.depth = depth;
        this.arrayed = arrayed;
        this.multisampled = multisampled;
        this.sampling = sampling;
        this.format = format;
        this.qualifier = qualifier;
    }

    public boolean isArrayed() {
        return arrayed;
    }

    public ImageDepth getDepth() {
        return depth;
    }

    public Dimensionality getDimensionality() {
        return dimensionality;
    }

    public ImageFormat getFormat() {
        return format;
    }

    public boolean isMultisampled() {
        return multisampled;
    }

    public AccessQualifier getQualifier() {
        return qualifier;
    }

    public Type getSampledType() {
        return sampledType;
    }

    public Sampling getSampling() {
        return sampling;
    }
}

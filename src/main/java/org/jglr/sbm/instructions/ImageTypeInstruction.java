package org.jglr.sbm.instructions;

import org.jglr.sbm.AccessQualifier;
import org.jglr.sbm.InfoPool;
import org.jglr.sbm.sampler.Dimensionality;
import org.jglr.sbm.sampler.ImageDepth;
import org.jglr.sbm.sampler.ImageFormat;
import org.jglr.sbm.sampler.Sampling;
import org.jglr.sbm.types.Type;

public class ImageTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultID;
    private final long sampledTypeID;
    private final Dimensionality dim;
    private final ImageDepth depth;
    private final boolean arrayed;
    private final boolean multisampled;
    private final Sampling sampling;
    private final ImageFormat format;
    private final AccessQualifier access;
    private Type sampledType;

    public ImageTypeInstruction(long resultID, long sampledTypeID, Dimensionality dim, ImageDepth depth, boolean arrayed, boolean multisampled, Sampling sampling, ImageFormat format, AccessQualifier access) {
        super(OpTypeImage, 9 + (access == null ? 0 : 1), resultID);
        this.resultID = resultID;
        this.sampledTypeID = sampledTypeID;
        this.dim = dim;
        this.depth = depth;
        this.arrayed = arrayed;
        this.multisampled = multisampled;
        this.sampling = sampling;
        this.format = format;
        this.access = access;
    }

    public AccessQualifier getAccess() {
        return access;
    }

    public boolean isArrayed() {
        return arrayed;
    }

    public ImageDepth getDepth() {
        return depth;
    }

    public Dimensionality getDim() {
        return dim;
    }

    public ImageFormat getFormat() {
        return format;
    }

    public boolean isMultisampled() {
        return multisampled;
    }

    @Override
    public long getResultID() {
        return resultID;
    }

    public Type getSampledType() {
        return sampledType;
    }

    public long getSampledTypeID() {
        return sampledTypeID;
    }

    public Sampling getSampling() {
        return sampling;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        sampledType = infoPool.getType(sampledTypeID);
    }

    @Override
    public String toString() {
        return "OpTypeImage "+nameOrID(sampledTypeID, sampledType)+" "+sampling.name()+
                ", multisampled: "+multisampled+", format: "+format.name()+", access: "+(access == null ? "n/a" : getAccess().name())+
                ", dimensionality: "+dim.name()+", depth: "+depth.name()+", arrayed: "+arrayed;
    }
}

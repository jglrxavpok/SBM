package org.jglr.sbm.types;

public class SampledImageType extends Type {
    private final Type imageType;

    public SampledImageType(Type imageType) {
        super("sampled("+imageType+")");
        this.imageType = imageType;
    }

    public Type getImageType() {
        return imageType;
    }
}

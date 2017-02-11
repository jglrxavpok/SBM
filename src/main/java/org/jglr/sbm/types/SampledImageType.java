package org.jglr.sbm.types;

public class SampledImageType extends Type {
    private final ImageType imageType;

    public SampledImageType(ImageType imageType) {
        super("sampled("+imageType+")");
        this.imageType = imageType;
    }

    public ImageType getImageType() {
        return imageType;
    }
}

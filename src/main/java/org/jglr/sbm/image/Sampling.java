package org.jglr.sbm.image;

public enum Sampling {

    /**
     * Only known at runtime, not compile time
     */
    KNOWN_AT_RUNTIME,
    WITH_SAMPLER,

    /**
     * Used for storage images
     */
    NO_SAMPLER
}

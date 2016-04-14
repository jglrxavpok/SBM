package org.jglr.sbm;

public abstract class MaskValue {

    protected long mask;

    public MaskValue(long mask) {
        setFromMask(mask);
    }

    public long getMask() {
        return mask;
    }

    public void setFromMask(long mask) {
        this.mask = mask;
    }

    protected abstract void updateMask();

    protected final boolean has(long bitPattern) {
        return (mask & bitPattern) != 0;
    }
}

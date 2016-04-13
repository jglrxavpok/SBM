package org.jglr.sbm;

public abstract class MaskValue {

    protected int mask;

    public MaskValue(int mask) {
        setFromMask(mask);
    }

    public int getMask() {
        return mask;
    }

    public void setFromMask(int mask) {
        this.mask = mask;
    }

    protected abstract void updateMask();

    protected final boolean has(int bitPattern) {
        return (mask & bitPattern) != 0;
    }
}

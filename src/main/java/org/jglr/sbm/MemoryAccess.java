package org.jglr.sbm;

public class MemoryAccess extends MaskValue {

    public static final int FLAG_VOLATILE = 0x1;
    public static final int FLAG_ALIGNED = 0x2;
    public static final int FLAG_NONTEMPORAL = 0x4;
    private boolean isVolatile;
    private boolean nontemporal;
    private boolean aligned;

    public MemoryAccess(long mask) {
        super(mask);
    }

    @Override
    public void setFromMask(long mask) {
        super.setFromMask(mask);
        isVolatile = has(FLAG_VOLATILE);
        aligned = has(FLAG_ALIGNED);
        nontemporal = has(FLAG_NONTEMPORAL);
    }

    @Override
    protected void updateMask() {
        long newMask = 0;
        if(isVolatile())
            newMask |= FLAG_VOLATILE;
        if(isAligned())
            newMask |= FLAG_ALIGNED;
        if(isNontemporal())
            newMask |= FLAG_NONTEMPORAL;
        this.mask = newMask;
    }

    public boolean isAligned() {
        return aligned;
    }

    public void setAligned(boolean aligned) {
        this.aligned = aligned;
    }

    public boolean isVolatile() {
        return isVolatile;
    }

    public void setVolatile(boolean aVolatile) {
        isVolatile = aVolatile;
    }

    public boolean isNontemporal() {
        return nontemporal;
    }

    public void setNontemporal(boolean nontemporal) {
        this.nontemporal = nontemporal;
    }
}

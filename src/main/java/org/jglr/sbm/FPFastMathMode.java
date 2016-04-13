package org.jglr.sbm;

public class FPFastMathMode extends MaskValue {

    public static final int FLAG_NOT_NAN = 0x1;
    public static final int FLAG_NOT_INF = 0x2;
    public static final int FLAG_INSIGNIFIENT_ZERO_SIGN = 0x4;
    public static final int FLAG_ALLOW_RECIPROCAL = 0x8;
    public static final int FLAG_FAST = 0x10;
    private int mask;
    private boolean assumeNotNaN;
    private boolean assumeNotInf;
    private boolean insignificantZeroSign;
    private boolean allowReciprocal;
    private boolean fast;

    public FPFastMathMode(int mask) {
        super(mask);
    }

    public int getMask() {
        return mask;
    }

    public boolean allowReciprocal() {
        return allowReciprocal;
    }

    public void allowReciprocal(boolean allowReciprocal) {
        this.allowReciprocal = allowReciprocal;
        updateMask();
    }

    public boolean assumeNotInf() {
        return assumeNotInf;
    }

    public void assumeNotInf(boolean assumeNotInf) {
        this.assumeNotInf = assumeNotInf;
        updateMask();
    }

    public boolean assumeNotNaN() {
        return assumeNotNaN;
    }

    public void assumeNotNaN(boolean assumeNotNaN) {
        this.assumeNotNaN = assumeNotNaN;
        updateMask();
    }

    public boolean isFast() {
        return fast;
    }

    public void setFast(boolean fast) {
        this.fast = fast;
        updateMask();
    }

    public boolean insignificantZeroSign() {
        return insignificantZeroSign;
    }

    public void insignificantZeroSign(boolean insignificantZeroSign) {
        this.insignificantZeroSign = insignificantZeroSign;
        updateMask();
    }

    public void setFromMask(int mask) {
        super.setFromMask(mask);
        assumeNotNaN = has(FLAG_NOT_NAN);
        assumeNotInf = has(FLAG_NOT_INF);
        insignificantZeroSign = has(FLAG_INSIGNIFIENT_ZERO_SIGN);
        allowReciprocal = has(FLAG_ALLOW_RECIPROCAL);
        fast = has(FLAG_FAST);
    }

    protected void updateMask() {
        int newMask = 0;
        if(assumeNotNaN())
            newMask |= 0x1;
        if(assumeNotInf())
            newMask |= 0x2;
        if(insignificantZeroSign())
            newMask |= 0x4;
        if(allowReciprocal())
            newMask |= 0x8;
        if(isFast())
            newMask |= 0x10;
        mask = newMask;
    }

}

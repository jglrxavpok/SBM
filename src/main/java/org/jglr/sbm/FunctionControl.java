package org.jglr.sbm;

public class FunctionControl extends MaskValue {

    public static final int FLAG_INLINE = 0x1;
    public static final int FLAG_DONT_INLINE = 0x2;
    public static final int FLAG_PURE = 0x4;
    public static final int FLAG_CONST = 0x8;
    private boolean inline;
    private boolean dontInline;
    private boolean pure;
    private boolean constFunc;

    public FunctionControl(long mask) {
        super(mask);
    }

    @Override
    public void setFromMask(long mask) {
        super.setFromMask(mask);
        inline = has(FLAG_INLINE);
        dontInline = has(FLAG_DONT_INLINE);
        pure = has(FLAG_PURE);
        constFunc = has(FLAG_CONST);
    }

    public boolean isConst() {
        return constFunc;
    }

    public void setConst(boolean constFunc) {
        this.constFunc = constFunc;
        updateMask();
    }

    public boolean dontInline() {
        return dontInline;
    }

    public void dontInline(boolean dontInline) {
        this.dontInline = dontInline;
        updateMask();
    }

    public boolean inline() {
        return inline;
    }

    public void inline(boolean inline) {
        this.inline = inline;
        updateMask();
    }

    public boolean isPure() {
        return pure;
    }

    public void setPure(boolean pure) {
        this.pure = pure;
        updateMask();
    }

    @Override
    protected void updateMask() {
        long newMask = 0;
        if(inline())
            newMask |= 0x1;
        if(dontInline())
            newMask |= 0x2;
        if(isPure())
            newMask |= 0x4;
        if(isConst())
            newMask |= 0x8;
        this.mask = newMask;
    }
}

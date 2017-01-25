package org.jglr.sbm.sampler;

import org.jglr.sbm.MaskValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImageOperands extends MaskValue {

    public static final int FLAG_BIAS = 0x1;
    public static final int FLAG_LOD = 0x2;
    public static final int FLAG_GRAD = 0x4;
    public static final int FLAG_CONST_OFFSET = 0x8;
    public static final int FLAG_OFFSET = 0x10;
    public static final int FLAG_CONST_OFFSETS = 0x20;
    public static final int FLAG_SAMPLE = 0x40;
    public static final int FLAG_MIN_LOD = 0x80;

    private boolean bias;
    private boolean lod;
    private boolean grad;
    private boolean constOffset;
    private boolean offset;
    private boolean constOffsets;
    private boolean sample;
    private boolean minLod;

    public ImageOperands(long mask) {
        super(mask);
    }

    public boolean isBias() {
        return bias;
    }

    public void setBias(boolean bias) {
        this.bias = bias;
    }

    public boolean isConstOffset() {
        return constOffset;
    }

    public void setConstOffset(boolean constOffset) {
        this.constOffset = constOffset;
    }

    public boolean isConstOffsets() {
        return constOffsets;
    }

    public void setConstOffsets(boolean constOffsets) {
        this.constOffsets = constOffsets;
    }

    public boolean isGrad() {
        return grad;
    }

    public void setGrad(boolean grad) {
        this.grad = grad;
    }

    public boolean isLod() {
        return lod;
    }

    public void setLod(boolean lod) {
        this.lod = lod;
    }

    public boolean isMinLod() {
        return minLod;
    }

    public void setMinLod(boolean minLod) {
        this.minLod = minLod;
    }

    public boolean isOffset() {
        return offset;
    }

    public void setOffset(boolean offset) {
        this.offset = offset;
    }

    public boolean isSample() {
        return sample;
    }

    public void setSample(boolean sample) {
        this.sample = sample;
    }

    @Override
    protected void updateMask() {
        long newMask = 0L;
        if(isBias())
            newMask |= FLAG_BIAS;
        if(isLod())
            newMask |= FLAG_LOD;
        if(isGrad())
            newMask |= FLAG_GRAD;
        if(isConstOffsets())
            newMask |= FLAG_CONST_OFFSET;
        if(isOffset())
            newMask |= FLAG_OFFSET;
        if(isConstOffsets())
            newMask |= FLAG_CONST_OFFSETS;
        if(isSample())
            newMask |= FLAG_SAMPLE;
        if(isMinLod())
            newMask |= FLAG_MIN_LOD;
        this.mask = newMask;
    }

    @Override
    public void setFromMask(long mask) {
        super.setFromMask(mask);
        bias = has(FLAG_BIAS);
        lod = has(FLAG_LOD);
        grad = has(FLAG_GRAD);
        constOffset = has(FLAG_CONST_OFFSET);
        offset = has(FLAG_OFFSET);
        constOffsets = has(FLAG_CONST_OFFSETS);
        sample = has(FLAG_SAMPLE);
        minLod = has(FLAG_MIN_LOD);
    }

    public int getOperandCount() {
        int result = 0;
        if(bias)
            result += 1;
        if(lod)
            result += 1;
        if(grad)
            result += 2;
        if(constOffset)
            result += 1;
        if(offset)
            result += 1;
        if(constOffsets)
            result += 1;
        if(sample)
            result += 1;
        if(minLod)
            result += 1;
        return result;
    }

    /**
     * Split operands to assign them to each image operand
     * @param operands
     *          The raw operands following the mask value inside the SPIR-V module
     * @param result
     *          Map to store the operands (Flag->Operand[]) (eg. {@see #FLAG_BIAS->Bias added to the implicit lod})
     */
    public void splitOperands(long[] operands, Map<Integer, long[]> result) {
        if(operands.length != getOperandCount())
            throw new IllegalArgumentException("Operand array size does not conform to required operand count (required: "+getOperandCount()+", found: "+operands.length+"");
        int position = 0;
        if(bias)
            set(result, FLAG_BIAS, operands[position++]);
        if(lod)
            set(result, FLAG_LOD, operands[position++]);
        if(bias)
            set(result, FLAG_GRAD, operands[position++], operands[position++]);
        if(constOffset)
            set(result, FLAG_CONST_OFFSET, operands[position++]);
        if(offset)
            set(result, FLAG_OFFSET, operands[position++]);
        if(constOffsets)
            set(result, FLAG_CONST_OFFSETS, operands[position++]);
        if(sample)
            set(result, FLAG_SAMPLE, operands[position++]);
        if(minLod)
            set(result, FLAG_MIN_LOD, operands[position++]);
    }

    private void set(Map<Integer, long[]> result, int key, long... values) {
        result.put(key, values);
    }

    public static long[] mergeOperands(Map<Integer, long[]> splitOperands) {
        List<Long> list = new ArrayList<>();
        int[] flags = {FLAG_BIAS, FLAG_LOD, FLAG_GRAD, FLAG_CONST_OFFSET, FLAG_OFFSET, FLAG_CONST_OFFSETS, FLAG_SAMPLE, FLAG_MIN_LOD};
        for (int f : flags) {
            long[] operands = splitOperands.getOrDefault(f, new long[0]);
            for(long l : operands) {
                list.add(l);
            }
        }

        long[] result = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }
}

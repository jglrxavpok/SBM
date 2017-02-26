package org.jglr.sbm.modes;

import org.jglr.sbm.ExecutionMode;

public class SizeExecutionMode extends ExecutionMode {
    private final long x;
    private final long y;
    private final long z;

    public SizeExecutionMode(Type type, long x, long y, long z) {
        super(type, x, y, z);
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public int getOperandCount() {
        return 3;
    }

    @Override
    public String toString() {
        return super.toString()+" "+x+","+y+","+z;
    }
}

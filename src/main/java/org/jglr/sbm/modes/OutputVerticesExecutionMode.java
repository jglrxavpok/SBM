package org.jglr.sbm.modes;

import org.jglr.sbm.ExecutionMode;

public class OutputVerticesExecutionMode extends ExecutionMode {
    private final long count;

    public OutputVerticesExecutionMode(long count) {
        super(Type.OutputVertices);
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return super.toString()+" "+count;
    }

    @Override
    public int getOperandCount() {
        return 1;
    }
}

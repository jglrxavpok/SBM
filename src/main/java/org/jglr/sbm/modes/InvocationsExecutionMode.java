package org.jglr.sbm.modes;

import org.jglr.sbm.ExecutionMode;

public class InvocationsExecutionMode extends ExecutionMode {
    private final long invocationCount;

    public InvocationsExecutionMode(long invocationCount) {
        super(Type.Invocations);
        this.invocationCount = invocationCount;
    }

    public long getInvocationCount() {
        return invocationCount;
    }

    @Override
    public int getOperandCount() {
        return 1;
    }

    @Override
    public String toString() {
        return "Invocations"+" "+invocationCount;
    }
}

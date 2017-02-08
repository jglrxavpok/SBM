package org.jglr.sbm;

public class BasicExecutionMode extends ExecutionMode {
    public BasicExecutionMode(Type t) {
        super(t);
    }

    @Override
    public int getOperandCount() {
        return 0;
    }
}

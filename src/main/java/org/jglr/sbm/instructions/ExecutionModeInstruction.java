package org.jglr.sbm.instructions;

import org.jglr.sbm.ExecutionMode;
import org.jglr.sbm.InfoPool;

public class ExecutionModeInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long entryPoint;
    private final ExecutionMode mode;
    private String entryPointName;

    public ExecutionModeInstruction(long entryPoint, ExecutionMode mode) {
        super(EXECUTION_MODE, 3 + mode.getOperandCount());
        this.entryPoint = entryPoint;
        this.mode = mode;
    }

    public long getEntryPoint() {
        return entryPoint;
    }

    public ExecutionMode getMode() {
        return mode;
    }

    @Override
    public String toString() {
        return "ExecutionMode "+nameOrID(entryPoint, entryPointName)+" = "+mode.toString();
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        entryPointName = infoPool.getName(entryPoint);
    }

    public String getEntryPointName() {
        return entryPointName;
    }
}

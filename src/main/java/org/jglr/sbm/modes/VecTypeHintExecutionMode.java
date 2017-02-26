package org.jglr.sbm.modes;

import org.jglr.sbm.ExecutionMode;

public class VecTypeHintExecutionMode extends ExecutionMode {

    private final long componentCount;
    private final DataType type;

    public enum DataType {
        Int8,
        Int16,
        Int32,
        Int64,
        Float16,
        Float32,
        Float64
    }

    public VecTypeHintExecutionMode(long componentCount, DataType type) {
        super(Type.VecTypeHint, componentCount, type.ordinal());
        this.componentCount = componentCount;
        this.type = type;
    }

    public long getComponentCount() {
        return componentCount;
    }

    public DataType getDataType() {
        return type;
    }

    @Override
    public int getOperandCount() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString()+" "+componentCount+" "+type.name();
    }
}

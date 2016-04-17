package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

public class MatrixTypeInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long columnTypeID;
    private final long columnCount;
    private Type columnType;

    public MatrixTypeInstruction(long resultID, long columnTypeID, long columnCount) {
        super(TypeMatrix, 4, resultID);
        this.columnTypeID = columnTypeID;
        this.columnCount = columnCount;
    }

    public long getColumnCount() {
        return columnCount;
    }

    public long getColumnTypeID() {
        return columnTypeID;
    }

    public Type getColumnType() {
        return columnType;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        columnType = infoPool.getType(columnTypeID);
    }

    @Override
    public String toString() {
        return "TypeVec "+ columnCount +" ("+nameOrID(columnTypeID, columnType)+")";
    }
}

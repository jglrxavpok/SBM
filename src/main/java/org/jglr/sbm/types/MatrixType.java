package org.jglr.sbm.types;

public class MatrixType extends Type {
    private final Type columnType;
    private final long columnCount;

    public MatrixType(Type columnType, long columnCount) {
        super("mat"+columnCount+"("+columnType+")");
        this.columnType = columnType;
        this.columnCount = columnCount;
    }

    public long getColumnCount() {
        return columnCount;
    }

    public Type getColumnType() {
        return columnType;
    }
}

package org.jglr.sbm.types;

public class MatrixType extends Type {
    private final Type columnType;
    private final int columnCount;

    public MatrixType(Type columnType, int columnCount) {
        super("mat"+columnCount+"("+columnType+")");
        this.columnType = columnType;
        this.columnCount = columnCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public Type getColumnType() {
        return columnType;
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;

public class LineInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long filenameID;
    private final long line;
    private final long column;
    private String filename;

    public LineInstruction(long filenameID, long line, long column) {
        super(LINE, 4);
        this.filenameID = filenameID;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return "Line "+nameOrID(filenameID, filename)+": "+line+";"+column;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        filename = constantPool.getString(filenameID);
    }
}

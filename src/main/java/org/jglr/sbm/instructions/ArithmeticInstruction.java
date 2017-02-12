package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.Opcodes;
import org.jglr.sbm.types.Type;

public class ArithmeticInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long leftID;
    private final long rightID;
    private Type resultType;
    private String leftName;
    private String rightName;

    public ArithmeticInstruction(int opcode, long resultTypeID, long resultID, long leftID, long rightID) {
        super(opcode, 5, resultID);
        this.resultTypeID = resultTypeID;
        this.leftID = leftID;
        this.rightID = rightID;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        leftName = infoPool.getName(leftID);
        rightName = infoPool.getName(rightID);
    }

    @Override
    public String toString() {
        return Opcodes.getName(getOpcode())+" "+nameOrID(resultTypeID, resultType)+" "+nameOrID(leftID, leftName)+" "+nameOrID(rightID, rightName);
    }
}

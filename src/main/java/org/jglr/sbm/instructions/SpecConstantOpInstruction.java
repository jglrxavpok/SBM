package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class SpecConstantOpInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long opcode;
    private final long[] operands;
    private String name;
    private Type resultType;

    public SpecConstantOpInstruction(long resultTypeID, long resultID, long opcode, long[] operands) {
        super(OpSpecConstantOp, 4 + operands.length, resultID);
        this.resultTypeID = resultTypeID;
        this.opcode = opcode;
        this.operands = operands;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long getSpecOpcode() {
        return opcode;
    }

    public long[] getOperands() {
        return operands;
    }

    @Override
    public String toString() {
        return "OpSpecConstantOp "+nameOrID(getResultID(), name)+" of type "+nameOrID(resultTypeID, resultType)+": "+opcode+ Arrays.toString(operands);
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
        resultType = infoPool.getType(resultTypeID);
    }
}

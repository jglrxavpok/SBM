package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.Opcodes;
import org.jglr.sbm.types.Type;

public abstract class NegateInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long operandID;
    private String name;
    private Type resultType;
    private String operandName;

    public NegateInstruction(int opcode, long resultTypeID, long resultID, long operandID) {
        super(opcode, 4, resultID);
        this.resultTypeID = resultTypeID;
        this.operandID = operandID;
    }

    public long getResultTypeID() {
        return resultTypeID;
    }

    public long getOperandID() {
        return operandID;
    }

    public String getName() {
        return name;
    }

    public Type getResultType() {
        return resultType;
    }

    public String getOperandName() {
        return operandName;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        name = infoPool.getName(getResultID());
        resultType = infoPool.getType(resultTypeID);
        operandName = infoPool.getName(operandID);
    }

    @Override
    public String toString() {
        return Opcodes.getName(getOpcode())+" "+nameOrID(resultTypeID, resultType)+" "+nameOrID(operandID, operandName);
    }
}

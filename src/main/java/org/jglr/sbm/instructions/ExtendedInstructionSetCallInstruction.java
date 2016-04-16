package org.jglr.sbm.instructions;

import org.jglr.sbm.ConstantPool;
import org.jglr.sbm.types.Type;

import java.util.Arrays;

public class ExtendedInstructionSetCallInstruction extends ResultInstruction implements ResolvableInstruction {
    private final long resultTypeID;
    private final long setID;
    private final long instruction;
    private final long[] operands;
    private Type resultType;
    private String set;

    public ExtendedInstructionSetCallInstruction(long resultTypeID, long resultID, long setID, long instruction, long[] operands) {
        super(EXT_INST, 5 + operands.length, resultID);
        this.resultTypeID = resultTypeID;
        this.setID = setID;
        this.instruction = instruction;
        this.operands = operands;
    }

    @Override
    public void onVisitEnd(ConstantPool constantPool) {
        resultType = constantPool.getType(resultTypeID);
        set = constantPool.getSet(setID);
    }

    @Override
    public String toString() {
        return "ExtInst "+nameOrID(setID, set)+": "+ Arrays.toString(operands)+nameOrID(resultTypeID, resultType)+" 0x"+Long.toHexString(instruction);
    }
}

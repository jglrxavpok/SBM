package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.glsl.GLSLStd450;
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
    public void onVisitEnd(InfoPool infoPool) {
        resultType = infoPool.getType(resultTypeID);
        set = infoPool.getSet(setID);
    }

    @Override
    public String toString() {
        String instructionStr = " 0x"+Long.toHexString(instruction);
        if(set != null && set.startsWith("GLSL.std.")) {
            instructionStr = " GLSL::"+ GLSLStd450.values()[(int) instruction];
        }
        return "ExtInst "+nameOrID(setID, set)+": "+ Arrays.toString(operands)+nameOrID(resultTypeID, resultType)+instructionStr;
    }
}

package org.jglr.sbm.instructions;

public class FloatNegateInstruction extends NegateInstruction {
    public FloatNegateInstruction(long resultTypeID, long resultID, long operandID) {
        super(OpFNegate, resultTypeID, resultID, operandID);
    }
}

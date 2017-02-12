package org.jglr.sbm.instructions;

public class IntegerNegateInstruction extends NegateInstruction {
    public IntegerNegateInstruction(long resultTypeID, long resultID, long operandID) {
        super(OpSNegate, resultTypeID, resultID, operandID);
    }
}

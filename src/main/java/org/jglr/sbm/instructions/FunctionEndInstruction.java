package org.jglr.sbm.instructions;

public class FunctionEndInstruction extends SpvInstruction {
    public FunctionEndInstruction() {
        super(OpFunctionEnd, 1);
    }

    @Override
    public String toString() {
        return "OpFunctionEnd";
    }
}

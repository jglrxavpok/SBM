package org.jglr.sbm.instructions;

public class FunctionEndInstruction extends SpvInstruction {
    public FunctionEndInstruction() {
        super(FUNCTION_END, 1);
    }

    @Override
    public String toString() {
        return "FunctionEnd";
    }
}

package org.jglr.sbm.instructions;

public class ModuleProcessedInstruction extends SpvInstruction {
    private final String process;

    public ModuleProcessedInstruction(String process) {
        super(OpModuleProcessed, 1);
        this.process = process;
    }

    public String getProcess() {
        return process;
    }

    @Override
    public String toString() {
        return "OpModuleProcessed: "+process;
    }
}

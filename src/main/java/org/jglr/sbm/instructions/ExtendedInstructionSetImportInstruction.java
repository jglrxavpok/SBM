package org.jglr.sbm.instructions;

public class ExtendedInstructionSetImportInstruction extends ResultInstruction {
    private final String name;

    public ExtendedInstructionSetImportInstruction(long resultID, String name) {
        super(EXT_INST_IMPORT, 2 + getWordCount(name), resultID);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ExtInstImport "+name;
    }
}

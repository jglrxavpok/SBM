package org.jglr.sbm.instructions;

public class ExtensionImportInstruction extends SpvInstruction {
    private final String extension;

    public ExtensionImportInstruction(String extension) {
        super(OpSourceExtension, getWordCount(extension) +1);
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "OpExtension import: "+extension;
    }
}

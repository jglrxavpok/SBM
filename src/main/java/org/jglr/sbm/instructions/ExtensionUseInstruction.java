package org.jglr.sbm.instructions;

public class ExtensionUseInstruction extends SpvInstruction {
    private final String extension;

    public ExtensionUseInstruction(String extension) {
        super(EXTENSION, getWordCount(extension)+1);
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "Extension use: "+extension;
    }
}

package org.jglr.sbm.instructions;

public class ExtensionUseInstruction extends SpvInstruction {
    private final String extension;

    public ExtensionUseInstruction(String extension) {
        super(OpExtension, getWordCount(extension)+1);
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "OpExtension use: "+extension;
    }
}

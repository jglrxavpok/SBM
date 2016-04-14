package org.jglr.sbm.instructions;

import org.jglr.sbm.Opcodes;
import org.jglr.sbm.types.Type;

public abstract class SpvInstruction implements Opcodes {
    private final int opcode;
    private final int wordCount;

    public SpvInstruction(int opcode, int wordCount) {
        this.opcode = opcode;
        this.wordCount = wordCount;
    }

    public int getOpcode() {
        return opcode;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getOperandCount() {
        return wordCount-1;
    }

    public String nameOrID(long id, String name) {
        return name == null ? "%"+id : name;
    }

    public String nameOrID(long id, Type type) {
        return type == null ? "%"+id : type.getName();
    }

    public static int getWordCount(String string) {
        return string.length()/4;
    }

    public abstract String toString();
}

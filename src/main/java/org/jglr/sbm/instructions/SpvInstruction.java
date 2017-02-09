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
        return "%"+ (name == null ? id : name);
    }

    public String nameOrID(long id, Type type) {
        return type == null ? "%"+id : type.getName();
    }

    public String[] namesOrID(long[] ids, String[] names) {
        String[] result = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            result[i] = nameOrID(ids[i], names[i]);
        }
        return result;
    }

    public String[] namesOrID(long[] ids, Type[] types) {
        String[] result = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            Type type = types[i];
            if(type == null) {
                result[i] = ""+ids[i];
            } else {
                result[i] = type.getName();
            }
        }
        return result;
    }

    public static int getWordCount(String string) {
        return string.length()/4;
    }

    public abstract String toString();
}

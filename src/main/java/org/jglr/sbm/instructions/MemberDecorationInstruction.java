package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.decorations.DecorationValue;
import org.jglr.sbm.types.Type;

public class MemberDecorationInstruction extends SpvInstruction implements ResolvableInstruction {
    private final int wordCount;
    private final DecorationValue decoration;
    private final long structureTypeID;
    private final long member;
    private Type structureType;

    public MemberDecorationInstruction(int wordCount, DecorationValue decoration, long structureTypeID, long member) {
        super(MEMBER_DECORATE, wordCount);
        this.wordCount = wordCount;
        this.decoration = decoration;
        this.structureTypeID = structureTypeID;
        this.member = member;
    }

    public long getMember() {
        return member;
    }

    public long getStructureTypeID() {
        return structureTypeID;
    }

    public DecorationValue getDecoration() {
        return decoration;
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    public Type getStructureType() {
        return structureType;
    }

    @Override
    public String toString() {
        return "MemberDecorate "+member+" in "+nameOrID(structureTypeID, structureType)+" with "+ decoration.toString();
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        structureType = infoPool.getType(structureTypeID);
    }
}

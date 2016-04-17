package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;
import org.jglr.sbm.decorations.DecorationValue;

public class DecorationInstruction extends SpvInstruction implements ResolvableInstruction {
    private final DecorationValue decoration;
    private final long target;
    private String targetName;

    public DecorationInstruction(int wordCount, DecorationValue decoration, long target) {
        super(OpDecorate, wordCount);
        this.decoration = decoration;
        this.target = target;
    }

    public String getTargetName() {
        return targetName;
    }

    public long getTarget() {
        return target;
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        targetName = infoPool.getName(target);
    }

    public DecorationValue getDecoration() {
        return decoration;
    }

    @Override
    public String toString() {
        return "OpDecorate "+nameOrID(target, targetName)+" with "+decoration.toString();
    }
}

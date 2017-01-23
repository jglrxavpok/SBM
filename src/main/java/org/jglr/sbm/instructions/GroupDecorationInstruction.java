package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;

public class GroupDecorationInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long decorationGroup;
    private final long[] targets;
    private String[] targetNames;
    private String decorationName;

    public GroupDecorationInstruction(long decorationGroup, long[] targets) {
        super(OpGroupDecorate, 2 + targets.length);
        this.decorationGroup = decorationGroup;
        this.targets = targets;
    }

    public long getDecorationGroup() {
        return decorationGroup;
    }

    public long[] getTargets() {
        return targets;
    }

    @Override
    public String toString() {
        return "OpGroupDecorate "+decorationName+" -> ("+String.join(", ", targetNames)+")";
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        targetNames = infoPool.getNames(targets);
        decorationName = infoPool.getName(decorationGroup);
    }
}

package org.jglr.sbm.instructions;

import org.jglr.sbm.InfoPool;

public class GroupDecorationMemberInstruction extends SpvInstruction implements ResolvableInstruction {
    private final long decorationGroup;
    private final long[] targets;
    private String[] targetNames;
    private String decorationName;

    public GroupDecorationMemberInstruction(long decorationGroup, long[] targets) {
        super(OpGroupMemberDecorate, 2 + targets.length);
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
        return "OpGroupMemberDecorate "+decorationName+" -> ("+String.join(", ", targetNames)+")";
    }

    @Override
    public void onVisitEnd(InfoPool infoPool) {
        targetNames = infoPool.getNames(targets);
        decorationName = infoPool.getName(decorationGroup);
    }
}

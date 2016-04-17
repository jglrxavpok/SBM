package org.jglr.sbm.instructions;

import org.jglr.sbm.AccessQualifier;

public class PipeTypeInstruction extends ResultInstruction {
    private final AccessQualifier accessQualifier;

    public PipeTypeInstruction(long resultID, AccessQualifier accessQualifier) {
        super(TYPE_PIPE, 3, resultID);
        this.accessQualifier = accessQualifier;
    }

    public AccessQualifier getAccessQualifier() {
        return accessQualifier;
    }

    @Override
    public String toString() {
        return "TypePipe "+accessQualifier.name();
    }
}

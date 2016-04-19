package org.jglr.sbm.types;

import org.jglr.sbm.AccessQualifier;

public class PipeType extends Type {
    private final AccessQualifier qualifier;

    public PipeType(AccessQualifier qualifier) {
        super("pipe"+qualifier.name());
        this.qualifier = qualifier;
    }

    public AccessQualifier getQualifier() {
        return qualifier;
    }
}

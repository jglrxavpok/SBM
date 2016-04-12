package org.jglr.sbm.types;

import org.jglr.sbm.AccessQualifier;

public class PipeType extends Type {
    public PipeType(AccessQualifier qualifier) {
        super("pipe"+qualifier.name());
    }
}

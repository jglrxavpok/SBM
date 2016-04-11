package org.jglr.sbm.types;

import java.util.Arrays;

public class StructType extends Type {
    private final Type[] structMembers;

    public StructType(Type[] structMembers) {
        super("struct"+ Arrays.toString(structMembers));
        this.structMembers = structMembers;
    }

    public Type[] getStructMembers() {
        return structMembers;
    }
}

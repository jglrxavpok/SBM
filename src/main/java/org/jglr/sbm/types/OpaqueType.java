package org.jglr.sbm.types;

public class OpaqueType extends Type {
    private final String name;

    public OpaqueType(String name) {
        super("opaque(\""+name+"\")");
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}

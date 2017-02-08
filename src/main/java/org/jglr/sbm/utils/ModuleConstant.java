package org.jglr.sbm.utils;

import org.jglr.sbm.types.Type;

public class ModuleConstant implements ModuleComponent {

    private String name;
    private final Type type;
    private final long[] bitPattern;

    public ModuleConstant(String name, Type type, long[] bitPattern) {
        this.name = name;
        this.type = type;
        this.bitPattern = bitPattern;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public long[] getBitPattern() {
        return bitPattern;
    }
}

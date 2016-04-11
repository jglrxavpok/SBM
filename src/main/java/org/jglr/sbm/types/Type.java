package org.jglr.sbm.types;

public class Type {

    private final String name;

    public Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

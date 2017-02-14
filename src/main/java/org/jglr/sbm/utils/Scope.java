package org.jglr.sbm.utils;

public class Scope {

    public static Scope GLOBAL = new Scope("<global>");
    private final String name;

    public Scope(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

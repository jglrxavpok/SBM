package org.jglr.sbm.utils;

public class ModuleVariable implements ModuleComponent {

    private final String name;

    public ModuleVariable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package org.jglr.sbm.types;

public class Type {

    public static final Type VOID = new Type("void");
    public static final Type BOOL = new Type("bool");
    public static final Type SAMPLER = new Type("sampler");
    public static final Type EVENT = new Type("event");
    public static final Type DEVICE_EVENT = new Type("deviceEvent");
    public static final Type QUEUE = new Type("queue");
    public static final Type RESERVE_ID = new Type("reserveID");
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

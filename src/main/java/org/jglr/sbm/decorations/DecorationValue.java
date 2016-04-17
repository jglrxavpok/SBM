package org.jglr.sbm.decorations;

import org.jglr.sbm.Decoration;

public class DecorationValue {

    private final Decoration type;

    public DecorationValue(Decoration type) {
        this.type = type;
    }

    public Decoration getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.name();
    }
}

package org.jglr.sbm.decorations;

import org.jglr.sbm.Decoration;
import org.jglr.sbm.LinkageType;

public class LinkageDecorationValue extends DecorationValue {
    private final String name;
    private final LinkageType type;

    public LinkageDecorationValue(String name, LinkageType type) {
        super(Decoration.LinkageAttributes);
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public LinkageType getLinkageType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString()+" "+name+" "+type.name();
    }
}

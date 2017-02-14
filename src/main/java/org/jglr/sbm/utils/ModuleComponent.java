package org.jglr.sbm.utils;

import org.jglr.sbm.types.Type;

public interface ModuleComponent {

    Type getType();

    String getName();

    default Scope getScope() {
        return Scope.GLOBAL;
    }

    default String getID() {
        return getName()+getScope().getName();
    }
}

package org.jglr.sbm.utils;

import org.jglr.sbm.types.PointerType;

public class ModulePointer extends ModuleVariable {
    public ModulePointer(ModuleVariable variable) {
        super(variable.getName()+"*", new PointerType(variable.getStorageClass(), variable.getType()));
    }
}

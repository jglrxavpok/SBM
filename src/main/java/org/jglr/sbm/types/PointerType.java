package org.jglr.sbm.types;

import org.jglr.sbm.StorageClass;

public class PointerType extends Type {
    private final StorageClass storageClass;
    private final Type type;

    public PointerType(StorageClass storageClass, Type type) {
        super(type+"*");
        this.storageClass = storageClass;
        this.type = type;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public Type getType() {
        return type;
    }
}

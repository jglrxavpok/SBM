package org.jglr.sbm.utils;

import org.jglr.sbm.StorageClass;
import org.jglr.sbm.decorations.DecorationValue;
import org.jglr.sbm.types.Type;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModuleVariable implements ModuleComponent {

    private final String name;
    private final Type type;
    private StorageClass storageClass;
    private List<DecorationValue> decorations;

    public ModuleVariable(String name, Type type) {
        this.name = name;
        this.type = type;
        setStorageClass(StorageClass.Function);
        decorations = new LinkedList<>();
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public StorageClass getStorageClass() {
        return storageClass;
    }

    public ModuleVariable setStorageClass(StorageClass storageClass) {
        this.storageClass = storageClass;
        return this;
    }

    public ModuleVariable addDecoration(DecorationValue decoration) {
        decorations.add(decoration);
        return this;
    }

    public List<DecorationValue> getDecorations() {
        return decorations;
    }
}

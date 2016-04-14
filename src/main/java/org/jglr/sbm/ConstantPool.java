package org.jglr.sbm;

import org.jglr.sbm.types.Type;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConstantPool {

    private final Map<Long, String> stringMap;
    private final Map<Long, String> nameMap;
    private final Map<Long, Map<Long, String>> memberNameMap;
    private final Map<Long, Type> typeMap;

    public ConstantPool() {
        stringMap = new HashMap<>();
        memberNameMap = new HashMap<>();
        nameMap = new HashMap<>();
        typeMap = new HashMap<>();
    }

    public void empty() {
        stringMap.clear();
    }

    public String getString(long stringID) {
        return stringMap.getOrDefault(stringID, null);
    }

    public String getName(long nameID) {
        return nameMap.getOrDefault(nameID, "name"+nameID);
    }

    public String getMemberName(long structureTypeID, long nameID) {
        return memberNameMap.getOrDefault(structureTypeID, Collections.emptyMap()).getOrDefault(nameID, "name"+nameID+","+structureTypeID);
    }

    public Type getType(long typeID) {
        return typeMap.getOrDefault(typeID, new Type("Waiting"+typeID));
    }

    public void registerType(long id, Type type) {
        typeMap.put(id, type);
    }

    public void registerName(long id, String name) {
        nameMap.put(id, name);
    }

    public void registerMemberName(long structureTypeID, long id, String name) {
        if(!memberNameMap.containsKey(structureTypeID))
            memberNameMap.put(structureTypeID, new HashMap<>());
        memberNameMap.get(structureTypeID).put(id, name);
    }

    public void registerString(long id, String string) {
        stringMap.put(id, string);
    }

    public Type[] getTypes(long[] typeIDs) {
        Type[] result = new Type[typeIDs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getType(typeIDs[i]);
        }
        return result;
    }
}

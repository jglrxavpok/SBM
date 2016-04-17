package org.jglr.sbm;

import org.jglr.sbm.decorations.DecorationValue;
import org.jglr.sbm.types.Type;

import java.util.*;

public class InfoPool {

    private final Map<Long, String> stringMap;
    private final Map<Long, String> nameMap;
    private final Map<Long, Map<Long, String>> memberNameMap;
    private final Map<Long, Type> typeMap;
    private final Map<Long, String> sets;
    private final Map<Long, List<DecorationValue>> decorations;
    private final Map<Long, Map<Long, List<DecorationValue>>> memberDecorations;

    public InfoPool() {
        stringMap = new HashMap<>();
        memberNameMap = new HashMap<>();
        nameMap = new HashMap<>();
        typeMap = new HashMap<>();
        sets = new HashMap<>();
        decorations = new HashMap<>();
        memberDecorations = new HashMap<>();
    }

    public void empty() {
        stringMap.clear();
    }

    public String getString(long stringID) {
        return stringMap.getOrDefault(stringID, null);
    }

    public String getName(long nameID) {
        String result = nameMap.getOrDefault(nameID, ""+nameID);
        if(result.replace(" ", "").replace("\0", "").isEmpty()) {
            return ""+nameID;
        }
        return result;
    }

    public String getMemberName(long structureTypeID, long nameID) {
        return memberNameMap.getOrDefault(structureTypeID, Collections.emptyMap()).getOrDefault(nameID, ""+nameID+","+structureTypeID);
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

    public String[] getNames(long[] typeIDs) {
        String[] result = new String[typeIDs.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = getName(typeIDs[i]);
        }
        return result;
    }

    public void registerSet(long resultID, String name) {
        sets.put(resultID, name);
    }

    public String getSet(long resultID) {
        return sets.get(resultID);
    }

    public void addDecoration(long target, DecorationValue decorationValue) {
        if(!decorations.containsKey(target)) {
            decorations.put(target, new LinkedList<>());
        }
        decorations.get(target).add(decorationValue);
    }

    public List<DecorationValue> getDecorations(long target) {
        return decorations.getOrDefault(target, Collections.emptyList());
    }

    public List<DecorationValue> getMemberDecorations(long structureType, long target) {
        return memberDecorations.getOrDefault(structureType, Collections.emptyMap()).getOrDefault(target, Collections.emptyList());
    }

    public void addMemberDecoration(long structureType, long member, DecorationValue decorationValue) {
        if(!memberDecorations.containsKey(structureType)) {
            memberDecorations.put(structureType, new HashMap<>());
        }
        Map<Long, List<DecorationValue>> decorationMap = memberDecorations.get(structureType);
        if(!decorationMap.containsKey(member)) {
            decorationMap.put(member, new LinkedList<>());
        }
        decorationMap.get(member).add(decorationValue);
    }
}

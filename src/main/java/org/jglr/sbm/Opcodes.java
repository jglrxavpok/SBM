package org.jglr.sbm;

import java.lang.reflect.Field;
import java.util.HashMap;

public interface Opcodes {

    int Nop = 0,
        Undef = 1,

        SourceContinued = 2,
        Source = 3,
        SourceExtension = 4,

        Name = 5,
        MemberName = 6,
        String = 7,
        Line = 8,

        NoLine = 317,


        Decorate = 71,
        MemberDecorate = 72,

        Capability = 17,
        Extension = 10,
        ExtInstImport = 11,
        ExtInst = 12,
        MemoryModel = 14,
        EntryPoint = 15,
        ExecutionMode = 16,

        Constant = 43,
        Function = 54,
        Variable = 59,
        Label = 248,
        FunctionEnd = 56,
        FunctionCall = 57,
        Load = 61,
        Store = 62,
        CopyMemory = 63,
        CopyMemorySized = 64,
        AccessChain = 65,

        ImageSampleImplicitLod = 87,

        Return = 253,


        TypeVoid = 19,
        TypeBool = 20,
        TypeInt = 21,
        TypeFloat = 22,
        TypeVec = 23,
        TypeMatrix = 24,
        TypeImage = 25,
        TypeSampler = 26,
        TypeSampledImage = 27,
        TypeArray = 28,
        TypeRuntimeArray = 29,
        TypeStruct = 30,
        TypeOpaque = 31,
        TypePointer = 32,
        TypeFunction = 33,
        TypeEvent = 34,
        TypeDeviceEvent = 35,
        TypeReservedID = 36,
        TypeQueue = 37,
        TypePipe = 38,
        TypeForwardPointer = 39,

        ConstantTrue = 40,
        ConstantFalse = 41

    ;

    HashMap<Integer, String> names = new HashMap<>();

    static String getName(int opcode) {
        if(names.isEmpty()) {
            Field[] fields = Opcodes.class.getDeclaredFields();
            for(Field field : fields) {
                if(field.getType() == Integer.TYPE) {
                    try {
                        int value = field.getInt(null);
                        if(!names.containsKey(value))
                            names.put(value, field.getName());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if(names.containsKey(opcode)) {
            return names.get(opcode);
        }
        return "NULL";
    }
}

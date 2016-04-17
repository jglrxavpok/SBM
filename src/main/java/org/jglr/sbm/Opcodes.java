package org.jglr.sbm;

import java.lang.reflect.Field;
import java.util.HashMap;

public interface Opcodes {

    int OpNop = 0,
        OpUndef = 1,

        OpSourceContinued = 2,
        OpSource = 3,
        OpSourceExtension = 4,

        OpName = 5,
        OpMemberName = 6,
        OpString = 7,
        OpLine = 8,

        OpNoLine = 317,


        OpDecorate = 71,
        OpMemberDecorate = 72,

        OpCapability = 17,
        OpExtension = 10,
        OpExtInstImport = 11,
        OpExtInst = 12,
        OpMemoryModel = 14,
        OpEntryPoint = 15,
        OpExecutionMode = 16,

        OpConstant = 43,
        OpFunction = 54,
        OpVariable = 59,
        OpLabel = 248,
        OpFunctionEnd = 56,
        OpFunctionCall = 57,
        OpLoad = 61,
        OpStore = 62,
        OpCopyMemory = 63,
        OpCopyMemorySized = 64,
        OpAccessChain = 65,

        OpImageSampleImplicitLod = 87,

        OpReturn = 253,


        OpTypeVoid = 19,
        OpTypeBool = 20,
        OpTypeInt = 21,
        OpTypeFloat = 22,
        OpTypeVec = 23,
        OpTypeMatrix = 24,
        OpTypeImage = 25,
        OpTypeSampler = 26,
        OpTypeSampledImage = 27,
        OpTypeArray = 28,
        OpTypeRuntimeArray = 29,
        OpTypeStruct = 30,
        OpTypeOpaque = 31,
        OpTypePointer = 32,
        OpTypeFunction = 33,
        OpTypeEvent = 34,
        OpTypeDeviceEvent = 35,
        OpTypeReservedID = 36,
        OpTypeQueue = 37,
        OpTypePipe = 38,
        OpTypeForwardPointer = 39,

        OpConstantTrue = 40,
        OpConstantFalse = 41

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

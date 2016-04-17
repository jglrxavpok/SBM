package org.jglr.sbm;

import java.lang.reflect.Field;
import java.util.HashMap;

public interface Opcodes {

    int NOP = 0,
        UNDEF = 1,

        SOURCE_CONTINUED = 2,
        SOURCE = 3,
        SOURCE_EXTENSION = 4,

        NAME = 5,
        MEMBER_NAME = 6,
        STRING = 7,
        LINE = 8,

        NO_LINE = 317,


        DECORATE = 71,
        MEMBER_DECORATE = 72,

        CAPABILITY = 17,
        EXTENSION = 10,
        EXT_INST_IMPORT = 11,
        EXT_INST = 12,
        MEMORY_MODEL = 14,
        ENTRY_POINT = 15,
        EXECUTION_MODE = 16,

        CONSTANT = 43,
        FUNCTION = 54,
        VARIABLE = 59,
        LABEL = 248,
        FUNCTION_END = 56,
        FUNCTION_CALL = 57,
        LOAD = 61,
        STORE = 62,
        COPY_MEMORY = 63,
        COPY_MEMORY_SIZED = 64,
        ACCESS_CHAIN = 65,

        RETURN = 253,


        TYPE_VOID = 19,
        TYPE_BOOL = 20,
        TYPE_INT = 21,
        TYPE_FLOAT = 22,
        TYPE_VEC = 23,
        TYPE_MATRIX = 24,
        TYPE_IMAGE = 25,
        TYPE_SAMPLER = 26,
        TYPE_SAMPLED_IMAGE = 27,
        TYPE_ARRAY = 28,
        TYPE_RUNTIME_ARRAY = 29,
        TYPE_STRUCT = 30,
        TYPE_OPAQUE = 31,
        TYPE_POINTER = 32,
        TYPE_FUNCTION = 33,
        TYPE_EVENT = 34,
        TYPE_DEVICE_EVENT = 35,
        TYPE_RESERVED_ID = 36,
        TYPE_QUEUE = 37,
        TYPE_PIPE = 38,
        TYPE_FORWARD_POINTER = 39,

        CONSTANT_TRUE = 40,
        CONSTANT_FALSE = 41

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

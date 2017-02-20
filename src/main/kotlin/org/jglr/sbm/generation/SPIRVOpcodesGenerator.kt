package org.jglr.sbm.generation

import com.beust.klaxon.JsonObject

object SPIRVOpcodesGenerator : ClassGenerator() {
    override val imports: List<String> = listOf("java.util.HashMap", "java.lang.reflect.Field")

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        val body = "if(names.isEmpty()) {"+
                    "\n    Field[] fields = Opcodes.class.getDeclaredFields();"+
                    "\n    for(Field field : fields) {"+
                    "\n        if(field.getType() == Integer.TYPE) {"+
                    "\n            try {"+
                    "\n                int value = field.getInt(null);"+
                    "\n                if(!names.containsKey(value))"+
                    "\n                    names.put(value, field.getName());"+
                    "\n            } catch (IllegalAccessException e) {"+
                    "\n                e.printStackTrace();"+
                    "\n            }"+
                    "\n        }"+
                    "\n    }"+
                    "\n}"+
                    "\nif(names.containsKey(opcode)) {"+
                    "\n    return names.get(opcode);"+
                    "\n}"+
                    "\nreturn \"NULL\";"
        members.add(ClassFunction("getName", "String", listOf("opcode"), listOf("int"), body, true))
    }

    override val headerComment = "Generated from spirv-opcodesonly.json"
    override val packageName = "org.jglr.sbm"
    override val title = "Opcodes"
    override val type = ClassType.INTERFACE
    override val headerDoc = null

    @Suppress("UNCHECKED_CAST")
    override fun fillMemberList(members: MutableList<ClassMember>): Unit {
        SPIRVOpcodes.map { instruction ->
            ClassMember(instruction["Name"] as String, "int", instruction["OpCode"].toString())
        }.toCollection(members)
        members.add(ClassMember("names", "HashMap<Integer, String>", "new HashMap<>()"))
    }

}
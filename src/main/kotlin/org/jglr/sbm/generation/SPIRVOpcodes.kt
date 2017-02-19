package org.jglr.sbm.generation

import com.beust.klaxon.JsonObject

object SPIRVOpcodes : ClassGenerator() {
    override val imports: List<String> = listOf("java.util.HashMap", "java.lang.reflect.Field")

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        val body = """if(names.isEmpty()) {
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
return "NULL";"""
        members.add(ClassFunction("getName", "String", listOf("opcode"), listOf("int"), body, true))
    }

    override val headerComment = "Generated from spirv.core.grammar.json"
    override val packageName = "org.jglr.sbm"
    override val title = "Opcodes"
    override val type = ClassType.INTERFACE
    override val headerDoc = (SPIRVGrammar["copyright"] as List<*>).joinToString { t -> t as String + "<br/>\n" }

    @Suppress("UNCHECKED_CAST")
    override fun fillMemberList(members: MutableList<ClassMember>): Unit {
        val instructions = (SPIRVGrammar["instructions"] as List<JsonObject>)
        instructions.map { instruction ->
            ClassMember(instruction["opname"] as String, "int", instruction["opcode"].toString())
        }.toCollection(members)
        members.add(ClassMember("names", "HashMap<Integer, String>", "new HashMap<>()"))
    }

}
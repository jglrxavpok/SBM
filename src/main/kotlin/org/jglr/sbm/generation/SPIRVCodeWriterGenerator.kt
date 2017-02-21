package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

object SPIRVCodeWriterGenerator : VisitorGenerator() {
    override val title: String = "CodeWriter0"

    override val packageName: String? = "org.jglr.sbm.visitors"

    override val type: ClassType = ClassType.CLASS

    override val imports: List<String> = listOf("org.jglr.flows.io.ByteArray",
            "org.jglr.sbm.*",
            "org.jglr.sbm.decorations.Decoration",
            "org.jglr.sbm.sampler.*",

            "java.io.UnsupportedEncodingException",
            "java.nio.ByteOrder",
            "java.util.Map")

    override val headerDoc: String? = "Generated from spirv-opcodesonly.json"

    override val interfaces: List<String> = listOf("CodeVisitor", "Opcodes")

    override fun fillMemberList(members: MutableList<ClassMember>) {
        members.add(ClassMember("buffer", "ByteArray", null, MemberAccess.PROTECTED))
    }

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        members.add(ClassFunction(title, "", emptyList(), emptyList(), "buffer = new ByteArray();\nbuffer.setByteOrder(ByteOrder.BIG_ENDIAN);"))
        members.add(ClassFunction("getBuffer", "ByteArray", emptyList(), emptyList(), "return buffer;"))
        members.add(ClassFunction("newOpcode", "void", listOf("opcode", "argCount"), listOf("int", "int"), "buffer.putUnsignedInt(((long)(argCount+1) << 16L | (opcode & 0xFFFF)));", false, MemberAccess.PRIVATE))
        members.add(ClassFunction("toBytes", "byte[]", emptyList(), emptyList(), "return buffer.backingArray();"))
        members.add(ClassFunction("writeChars", "void", listOf("chars"), listOf("String"), "ByteOrder order = buffer.getByteOrder();\nbuffer.setByteOrder(ByteOrder.LITTLE_ENDIAN);\nbuffer.putChars(chars);\nbuffer.setByteOrder(order);", false, MemberAccess.PRIVATE))
        members.add(ClassFunction("sizeOf", "int", listOf("string"), listOf("String"),
                "if(string == null)\n" +
                "    return 0;\n" +
                "int size = (int) Math.ceil((string.length()) / 4f);\n" +
                "try {\n" +
                "    byte[] bytes = string.getBytes(\"UTF-8\");\n" +
                "    if(bytes.length % 4 == 0)\n" +
                "        size++;\n" +
                "} catch (UnsupportedEncodingException e) {\n" +
                "    e.printStackTrace();\n" +
                "}\n" +
                "return size;\n",
                false, MemberAccess.PRIVATE))

        SPIRVOpcodes.forEach { op ->
            val opname = op["Name"]as String
            val functionName =
                when(op["Category"]) {
                    "Type-Declaration" -> {
                        SPIRVTypeVisitorGenerator.transformTypeOpName(opname)
                    }
                    else -> {
                        "visit$opname"
                    }
                }
            members.add(createWriteFunction(functionName, op))
        }
    }

    private fun createWriteFunction(name: String, instruction: JsonObject): ClassFunction {
        val argumentNames = mutableListOf<String>()
        val argumentTypes = mutableListOf<String>()
        @Suppress("UNCHECKED_CAST")
        val operands = instruction["Operands"] as JsonArray<JsonObject>

        fillOperandNameAndTypes(operands, argumentNames, argumentTypes)
        var argCount = argumentTypes.mapIndexed { i, t -> sizeStringOf(argumentNames[i], t)}.joinToString(" + ") { size -> size }
        if(argCount.isBlank()) {
            argCount = "0"
        }
        var body = "newOpcode(${instruction["Name"] as String}, $argCount);"
        val function = ClassFunction(name, "void", argumentNames, argumentTypes, body)
        function.documentation = instruction["DescriptionPlain"] as String?
        function.documentation?.let { function.documentation = function.documentation?.replace("\n", "\n<br/>") }
        return function
    }

    private fun sizeStringOf(name: String, type: String): String {
        if(name.startsWith("optional")) {
            if(type == "long") {
                return "($name == -1 ? 0 : 1)"
            } else {
                return "($name != null ? 1 : 0)"
            }
        }
        when(name) {
            "ImageOperands" -> {
                return "($name.getMask() == 0 ? 0 : 1 + $name.getOperandCount())"
            }
        }
        if(type.endsWith("[]")) {
            return "$name.length"
        }
        return when(type) {
            "String" -> {
               "sizeOf($name)"
            }
            else -> {
                "1"
            }
        }
    }
}
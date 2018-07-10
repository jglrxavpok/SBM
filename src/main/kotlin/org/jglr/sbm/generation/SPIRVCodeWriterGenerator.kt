package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import org.jglr.flows.io.IndentableWriter
import java.io.StringWriter

object SPIRVCodeWriterGenerator : VisitorGenerator() {
    override val title: String = "CodeWriter"

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

    override fun writeFooter(writer: IndentableWriter) {
        super.writeFooter(writer)
        javaClass.getResourceAsStream("/generation/fragments/CodeWriterDecorations.txt").bufferedReader()
                .lines().forEach { l -> writer.write("\n$l") }
    }

    override fun fillMemberList(members: MutableList<ClassMember>) {
        members.add(ClassMember("buffer", "ByteArray", null, MemberAccess.PROTECTED))
    }

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        members.add(ClassFunction(title, "", emptyList(), emptyList(), "buffer = new ByteArray();\nbuffer.setByteOrder(ByteOrder.BIG_ENDIAN);"))
        members.add(ClassFunction("getBuffer", "ByteArray", emptyList(), emptyList(), "return buffer;"))
        members.add(ClassFunction("newOpcode", "void", listOf("opcode", "argCount"), listOf("int", "int"), "buffer.putUnsignedInt(((long)(argCount+1) << 16L | (opcode & 0xFFFF)));", false, MemberAccess.PRIVATE))
        members.add(ClassFunction("toBytes", "byte[]", emptyList(), emptyList(), "return buffer.backingArray();"))
        members.add(ClassFunction("visitEnd", "void", emptyList(), emptyList(), "").apply { annotations = listOf("Override") })
        members.add(ClassFunction("reset", "void", emptyList(), emptyList(), "buffer.reset();").apply { annotations = listOf("Override") })
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

        SPIRVOpcodes.filter{ op -> op["Category"] != "Annotation" }
                    .forEach { op ->
            val opname = op["Name"]as String
            val functionName = getCorrespondingVisitFunction(op)
            members.add(createWriteFunction(opname, functionName, op))
        }
    }

    private fun createWriteFunction(opname: String, name: String, instruction: JsonObject): ClassFunction {
        val argumentNames = mutableListOf<String>()
        val argumentTypes = mutableListOf<String>()
        @Suppress("UNCHECKED_CAST")
        val operands = instruction["Operands"] as JsonArray<JsonObject>

        if(opname == "OpExecutionMode") {
            argumentNames.addAll(listOf("entryPoint", "mode"))
            argumentTypes.addAll(listOf("long", "ExecutionMode"))
        } else {
            fillOperandNameAndTypes(opname, operands, argumentNames, argumentTypes)
        }
        var argCount = argumentTypes.mapIndexed { i, t -> sizeStringOf(argumentNames[i], t)}.joinToString(" + ") { size -> size }
        if(argCount.isBlank()) {
            argCount = "0"
        }
        var body = "newOpcode(${instruction["Name"] as String}, $argCount);"
        if(argumentNames.isNotEmpty())
            body += "\n"
            for (i in 0..argumentNames.size-1) {
                body += writeOperand(argumentNames[i], argumentTypes[i])
            }
        val function = ClassFunction(name, "void", argumentNames, argumentTypes, body)
        function.documentation = instruction["DescriptionPlain"] as String?
        function.documentation?.let { function.documentation = function.documentation?.replace("\n", "\n<br/>") }
        function.annotations = listOf("Override")
        return function
    }

    private fun writeOperand(opName: String, opType: String): String {
        val name = opName.replace("$", "")
        if("optional" !in opName || opName.startsWith("$")) {
            when(opType) {
                "long" -> return "buffer.putUnsignedInt($name);\n"
                "boolean" -> return "buffer.putUnsignedInt($name ? 1 : 0);\n"
                "long[]" -> return "buffer.putUnsignedInts($name);\n"
                "String" -> return "writeChars($name);\n"
                "SourceLanguage", "AddressingModel", "MemoryModel", "ExecutionModel", "Capability", "Dimensionality",
                "ImageDepth", "Sampling", "ImageFormat", "StorageClass", "AccessQualifier", "SamplerAddressingMode",
                "SamplerFilterMode" -> return "buffer.putUnsignedInt($name.ordinal());\n"
                "MemoryAccess", "FunctionControl" -> return "buffer.putUnsignedInt($name.getMask());\n"
                "ExecutionMode" -> return "buffer.putUnsignedInt($name.getType().ordinal());\nbuffer.putUnsignedInts($name.getOperands());\n"
                "Map<Integer, long[]>" -> return ""
                "ImageOperands" -> return "\nbuffer.putUnsignedInt($name.getMask());\nlong[] ${name}_operandValues = ImageOperands.mergeOperands(splitOperands);\nfor (long o : ${name}_operandValues) buffer.putUnsignedInt(o);\n"
                else -> error("Unsupported: $opType")
            }
        }
        val stringWriter = StringWriter()
        val writer = IndentableWriter(stringWriter)
        with(writer) {
            if(opType.endsWith("[]")) {
                val elemType = opType.substring(0, opType.length-2)
                write("if($name != null) {")
                incrementIndentation()
                    write("\nfor($elemType e_$name : $name) {")
                    incrementIndentation()
                        write("\n")
                        write(writeOperand("\$e_$name", elemType))
                    decrementIndentation()
                    write("\n}\n")
                decrementIndentation()
                write("\n}\n")
            } else if(opName.startsWith("optional")) {
                if (opType != "long") {
                    write("if($name != null) {")
                    incrementIndentation()
                    write("\n")
                } else {
                    write("if($name != -1) {")
                    incrementIndentation()
                    write("\n")
                }
                    write(writeOperand("$$name", opType))
                decrementIndentation()
                write("\n}\n")
            }
        }
        writer.flush()
        writer.close()
        return stringWriter.toString()
    }

    private fun sizeStringOf(name: String, type: String): String {
        val n = name.replace("$", "")
        if(name.startsWith("optional") && "$" !in name) {
            if(type == "long") {
                return "($n == -1 ? 0 : 1)"
            } else {
                return "($n != null ? "+ sizeStringOf("$$n", type)+" : 0)"
            }
        }
        when(name) {
            "ImageOperands" -> {
                return "($n.getMask() == 0 ? 0 : 1 + $n.getOperandCount())"
            }
        }
        if(type.endsWith("[]")) {
            return "$n.length"
        }
        return when(type) {
            "String" -> {
               "sizeOf($n)"
            }
            else -> {
                "1"
            }
        }
    }
}
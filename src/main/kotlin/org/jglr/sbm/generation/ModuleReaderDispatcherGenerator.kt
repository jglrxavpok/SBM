package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import org.jglr.flows.io.IndentableWriter
import java.io.StringWriter

object ModuleReaderDispatcherGenerator : VisitorGenerator() {
    override val title: String = "ModuleReaderDispatcher0"
    override val packageName: String? = "org.jglr.sbm.visitors"
    override val type: ClassType = ClassType.CLASS
    override val imports: List<String> = listOf("org.jglr.sbm.*",
            "org.jglr.sbm.decorations.Decoration",
            "org.jglr.sbm.sampler.*",
            "java.io.IOException",
            "java.util.Collections",
            "java.util.HashMap",
            "java.util.Map")
    override val headerDoc: String? = null
    override val interfaces: List<String> = listOf("Opcodes")

    override fun fillMemberList(members: MutableList<ClassMember>) {
        members.add(ClassMember("reader", "ModuleReader", null, MemberAccess.PRIVATE))
    }

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        members.add(ClassFunction("toBoolean", "boolean", listOf("value"), listOf("long"),
                "if(value != 0 && value != 1) throw new IllegalArgumentException(\"Must be 0 or 1 to convert to boolean (was \"+value+\")\");\nreturn value != 0;", false, MemberAccess.PRIVATE))
        members.add(ClassFunction(title, "", listOf("reader"), listOf("ModuleReader"),
                "this.reader = reader;", false, MemberAccess.PUBLIC))
        val dispatchBody = createDispatchBody()
        val dispatchFunction = ClassFunction("dispatch", "void", listOf("opcodeID", "wordCount", "visitor"), listOf("int", "int", "CodeVisitor"), dispatchBody)
        dispatchFunction.exceptions = listOf("IOException")
        members.add(dispatchFunction)
    }

    private fun createDispatchBody(): String {
        val stringWriter = StringWriter()
        val writer = IndentableWriter(stringWriter)
        with(writer) {
            write("switch (opcodeID) {")
            incrementIndentation()
                write("\n")
                SPIRVOpcodes.forEach { op ->
                    writeDispatchCase(op)
                }

                write("\ndefault:")
                incrementIndentation()
                write("\nSystem.err.println(\"Unhandled: \" + Opcodes.getName(opcodeID) + \" \" + opcodeID + \" / \" + wordCount+\" at \"+reader.position);" +
                        "\nreader.position += (wordCount-1)*4;" +
                        "\nbreak;")
                decrementIndentation()
                decrementIndentation()
            write("\n}")
        }
        writer.flush()
        writer.close()
        return stringWriter.toString()
    }

    private fun IndentableWriter.writeDispatchCase(instruction: JsonObject) {
        val opName = instruction["Name"] as String
        write("case $opName: {")
        incrementIndentation()
        write("\n")
        val names = mutableListOf<String>()
        val types = mutableListOf<String>()
        @Suppress("UNCHECKED_CAST")
        val operands = instruction["Operands"] as JsonArray<JsonObject>
        fillOperandNameAndTypes(opName, operands, names, types)
        names.forEachIndexed { i, name ->
            val type = types[i]
            writeOperandValueRead(i, name, type)
        }
        val functionName = "visit${opName.substring(2)}"
        val args = if(names.isEmpty()) "" else names.reduce { a, b -> a+", "+b }
        write("visitor.$functionName($args);")
        decrementIndentation()
        write("\n}\nbreak;\n\n")
    }

    private fun IndentableWriter.writeOperandValueRead(i: Int, opname: String, type: String) {
        val name = opname.replace("$", "")
        if("optional" in opname && "$" !in opname) {
            if(type == "long")
                write("long $name = -1;\n")
            else
                write("$type $name = null;\n")
            write("if(wordCount > ${i+1}) {")
            incrementIndentation()
            write("\n")
            writeOperandValueRead(i, "$$name", type)
            decrementIndentation()
            write("\n}\n")
            return
        }
        val offsetStr = if(i != 0) "-$i" else ""
        val value = when(type) {
            "long" -> "reader.nextWord()"
            "long[]" -> "reader.nextWords(wordCount$offsetStr)"
            "String" -> "reader.nextString(wordCount$offsetStr)"
            "boolean" -> "toBoolean(reader.nextWord())"
            "SourceLanguage", "AddressingModel", "MemoryModel", "ExecutionModel", "Capability", "Dimensionality",
            "ImageDepth", "Sampling", "ImageFormat", "StorageClass", "AccessQualifier", "SamplerAddressingMode",
            "SamplerFilterMode" -> "reader.nextEnumValue($type.values())"
            else -> "null /* FIXME */"
        }
        if('$' in opname) // already initialized
            write("$name = $value;\n")
        else
            write("$type $name = $value;\n")
    }
}
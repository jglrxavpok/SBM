package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import org.jglr.flows.io.IndentableWriter
import java.io.StringWriter

object SPIRVCodeVisitorGenerator : VisitorGenerator() {
    override val title: String = "CodeVisitor"

    override val extends: String? = "TypeVisitor, DecorationVisitor, ConstantVisitor, FunctionVisitor, MemoryVisitor"
    override val packageName: String? = "org.jglr.sbm.visitors"

    override val type: ClassType = ClassType.INTERFACE

    override val imports: List<String> = listOf("org.jglr.flows.io.ByteArray",
            "org.jglr.sbm.*",
            "org.jglr.sbm.decorations.Decoration",
            "org.jglr.sbm.sampler.*",

            "java.io.UnsupportedEncodingException",
            "java.nio.ByteOrder",
            "java.util.Map")

    override val headerDoc: String? = "Generated from spirv-opcodesonly.json"

    override val interfaces: List<String> = listOf()

    override fun writeFooter(writer: IndentableWriter) {

    }

    override fun fillMemberList(members: MutableList<ClassMember>) {

    }

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        members.add(BodylessClassFunction("reset", "void", emptyList(), emptyList()))
        members.add(BodylessClassFunction("visitEnd", "void", emptyList(), emptyList()))
        SPIRVOpcodes.filter{ op -> op["Category"] != "Annotation"
                && op["Category"] != "Memory" && op["Category"] != "Annotation"
                && op["Category"] != "Function" && op["Category"] != "Type-Declaration" }
                    .forEach { op ->
            val opname = op["Name"]as String
            val functionName = getCorrespondingVisitFunction(op)
            members.add(createVisitorFunction(opname, functionName, op))
        }
    }

    private fun createVisitorFunction(opname: String, name: String, instruction: JsonObject): ClassFunction {
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
        val function = ClassFunction(name, "void", argumentNames, argumentTypes, "")
        function.documentation = instruction["DescriptionPlain"] as String?
        function.documentation?.let { function.documentation = function.documentation?.replace("\n", "\n<br/>") }
        function.bodyless = true
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
                else -> println("Unknown: "+opType)
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
                    write("if($name != null) {\n")
                } else {
                    write("if($name != -1) {\n")
                }
                incrementIndentation()
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
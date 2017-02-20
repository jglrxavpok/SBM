package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

abstract class VisitorGenerator : ClassGenerator() {
    protected fun createVisitFunction(name: String, instruction: JsonObject): ClassFunction {
        val argumentNames = mutableListOf<String>()
        val argumentTypes = mutableListOf<String>()
        @Suppress("UNCHECKED_CAST")
        val operands = instruction["Operands"] as JsonArray<JsonObject>

        operands.forEachIndexed { i, infos ->
            val opname = infos["Name"] as String
            argumentNames.add(opname.decapitalize())

            val type = getType(opname, infos["Type"] as String)
            argumentTypes.add(type)
        }
        val function = ClassFunction(name, "void", argumentNames, argumentTypes, "")
        function.documentation = instruction["DescriptionPlain"] as String?
        function.documentation?.let { function.documentation = function.documentation?.replace("\n", "\n<br/>") }
        function.bodyless = true
        return function
    }

    private fun getType(name: String, typeID: String): String {
        if(typeID.endsWith("?")) {
            return getType(name, typeID.substring(0, typeID.length-1))
        }
        if(typeID.endsWith("[]")) {
            return getType(name, typeID.substring(0, typeID.length-2))+"[]"
        }
        when (typeID) {
            "Dim" -> {
                return "Dimensionality"
            }
            "ImageFormat" -> {
                return "ImageFormat"
            }
            "StorageClass" -> {
                return "StorageClass"
            }
            "AccessQualifier" -> {
                return "AccessQualifier"
            }
            "LiteralString" -> {
                return "String"
            }
        }
        when (name) {
            "Arrayed" -> {
                return "boolean"
            }
            "MS" -> {
                return "boolean"
            }
            "Sampled" -> {
                return "Sampling"
            }
            "Depth" -> {
                return "ImageDepth"
            }
            "Signedness" -> {
                return "boolean"
            }
        }
        return "long"
    }
}
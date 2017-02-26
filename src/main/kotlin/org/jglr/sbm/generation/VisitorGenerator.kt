package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject

abstract class VisitorGenerator : ClassGenerator() {
    protected fun createVisitFunction(opname: String, name: String, instruction: JsonObject): ClassFunction {
        val argumentNames = mutableListOf<String>()
        val argumentTypes = mutableListOf<String>()
        @Suppress("UNCHECKED_CAST")
        val operands = instruction["Operands"] as JsonArray<JsonObject>
        fillOperandNameAndTypes(opname, operands, argumentNames, argumentTypes)
        val function = ClassFunction(name, "void", argumentNames, argumentTypes, "")
        function.documentation = instruction["DescriptionPlain"] as String?
        function.documentation?.let { function.documentation = function.documentation?.replace("\n", "\n<br/>") }
        function.bodyless = true
        return function
    }

    fun fillOperandNameAndTypes(instructionName: String, operands: JsonArray<JsonObject>, argumentNames: MutableList<String>, argumentTypes: MutableList<String>) {
        operands.forEachIndexed { i, infos ->
            var opname = if(infos["Name"] == null) "nullNamePleaseFix" else infos["Name"] as String
            val readType = if(infos["Type"] == null) "NullTypePleaseFix" else infos["Type"] as String
            var type = getType(opname, readType)
            if(opname == "Param" && instructionName == "OpConstantSampler") {
                type = "boolean"
            }
            when(opname) {
                "Optional" -> {
                    opname = "optional"+type.capitalize().replace("[]", "Array")
                }
                "Default" -> {
                    opname = "defaultValue"
                }
                else -> {
                    opname = opname.decapitalize()
                }
            }

            if(argumentNames.contains(opname)) {
                var index = 2
                while(argumentNames.contains(opname+index)) {
                    index++
                }
                opname += index
            }
            argumentNames.add(opname)

            argumentTypes.add(type)
        }

        if(argumentTypes.contains("ImageOperands")) {
            val index = argumentTypes.indexOfFirst { t -> t == "ImageOperands" }
            argumentTypes[index+1] = "Map<Integer, long[]>"
            argumentNames[index+1] = "splitOperands"
        }
    }

    protected fun getType(name: String, typeID: String): String {
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
            "FunctionControl" -> {
                return "FunctionControl"
            }
            "SamplerFilterMode" -> {
                return "SamplerFilterMode"
            }
            "Capability" -> {
                return "Capability"
            }
            "SourceLanguage" -> {
                return "SourceLanguage"
            }
            "SamplerAddressingMode" -> {
                return "SamplerAddressingMode"
            }
            "ExecutionMode" -> {
                return "ExecutionMode"
            }
            "MemoryModel" -> {
                return "MemoryModel"
            }
            "AddressingModel" -> {
                return "AddressingModel"
            }
            "ImageOperands" -> {
                return "ImageOperands"
            }
            "ExecutionModel" -> {
                return "ExecutionModel"
            }
            "MemoryAccess" -> {
                return "MemoryAccess"
            }
        }
        if(typeID.startsWith("LiteralString"))
            return "String"
        if(name.startsWith("LiteralString"))
            return "String"
        return when (name) {
            "Arrayed" -> {
                "boolean"
            }
            "MS" -> {
                "boolean"
            }
            "Sampled" -> {
                "Sampling"
            }
            "Depth" -> {
                "ImageDepth"
            }
            "Signedness" -> {
                "boolean"
            }
            else -> {
                "long"
            }
        }
    }
}
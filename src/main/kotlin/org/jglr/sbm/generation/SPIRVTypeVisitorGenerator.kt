package org.jglr.sbm.generation

import com.beust.klaxon.JsonObject

object SPIRVTypeVisitorGenerator : VisitorGenerator() {
    override val imports: List<String> = listOf("org.jglr.sbm.AccessQualifier",
            "org.jglr.sbm.StorageClass",
            "org.jglr.sbm.sampler.Dimensionality",
            "org.jglr.sbm.sampler.ImageDepth",
            "org.jglr.sbm.sampler.ImageFormat",
            "org.jglr.sbm.sampler.Sampling")

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        SPIRVOpcodes.forEach { op ->
            if(op["Category"] != "Type-Declaration") {
                return@forEach
            }
            val opname = op["Name"]as String
            val type = opname.substring("OpType".length).capitalize()
            val functionName = "visit${type}Type"
            val function = createVisitFunction(functionName, op)
            members.add(function)
        }
    }

    override val headerComment = "Generated from spirv-opcodesonly.json"
    override val packageName = "org.jglr.sbm.visitors"
    override val title = "TypeVisitor"
    override val type = ClassType.INTERFACE
    override val headerDoc = null

    override fun fillMemberList(members: MutableList<ClassMember>): Unit {}

}

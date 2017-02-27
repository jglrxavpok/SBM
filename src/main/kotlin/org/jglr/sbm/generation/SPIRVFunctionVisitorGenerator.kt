package org.jglr.sbm.generation

import com.beust.klaxon.JsonObject

object SPIRVFunctionVisitorGenerator : VisitorGenerator() {
    override val imports: List<String> = listOf("org.jglr.sbm.FunctionControl", "org.jglr.sbm.AddressingModel",
            "org.jglr.sbm.MemoryModel", "org.jglr.sbm.ExecutionModel", "org.jglr.sbm.ExecutionMode",
            "org.jglr.sbm.Capability")

    override fun fillFunctionList(members: MutableList<ClassFunction>) {
        SPIRVOpcodes.forEach { op ->
            if(op["Category"] != "Function") {
                return@forEach
            }
            val opname = op["Name"] as String
            val functionName = "visit${opname.substring(2)}"
            val function = createVisitFunction(opname, functionName, op)
            members.add(function)
        }
    }

    override val headerComment = "Generated from spirv-opcodesonly.json"
    override val packageName = "org.jglr.sbm.visitors"
    override val title = "FunctionVisitor"
    override val type = ClassType.INTERFACE
    override val headerDoc = null

    override fun fillMemberList(members: MutableList<ClassMember>): Unit {}

}

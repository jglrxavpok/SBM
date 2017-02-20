package org.jglr.sbm.generation

import org.jglr.flows.io.IndentableWriter
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

abstract class ClassGenerator {

    abstract val title: String
    abstract val packageName: String?
    abstract val type: ClassType
    abstract val imports: List<String>
    open val headerComment = "Auto-generated from "+javaClass.canonicalName

    abstract val headerDoc: String?

    fun generate(output: File) {
        if(!output.parentFile.exists())
            output.parentFile.mkdirs()
        val writer = IndentableWriter(BufferedWriter(FileWriter(output)))
        val members = mutableListOf<ClassMember>()
        val functions = mutableListOf<ClassFunction>()
        with(writer) {
            write("// $headerComment\n")
            packageName?.let { write("package $packageName;\n\n") }
            imports.forEach { i -> write("import $i;\n") }
            headerDoc?.let { write("\n/**\n$headerDoc\n*/\n") }
            write("\npublic ${type.name.toLowerCase()} $title {")
            incrementIndentation()
            write("\n\n")
            fillMemberList(members)
            fillFunctionList(functions)
            members.forEach { m ->
                m.documentation?.let { write("/**\n${m.documentation}\n*/\n") }
                if( ! (m.access == MemberAccess.PUBLIC && type == ClassType.INTERFACE))
                    write(m.access.name.toLowerCase()+" ")
                write("${m.type} ${m.name}")
                m.value?.let { v -> write(" = $v") }
                write(";\n")
            }
            functions.forEach { f ->
                write("\n")
                f.documentation?.let { write("/**\n${f.documentation}\n*/\n") }
                if( ! (f.access == MemberAccess.PUBLIC && type == ClassType.INTERFACE))
                    write(f.access.name.toLowerCase()+" ")
                if(f.static)
                    write("static ")
                write("${f.returnType} ${f.name}(")
                for (i in 0..f.argumentNames.size-1) {
                    if(i != 0)
                        write(", ")
                    write(f.argumentTypes[i]+" "+f.argumentNames[i])
                }
                write(")")
                if(f.bodyless) {
                    write(";\n")
                } else {
                    write(" {")
                    incrementIndentation()
                    write("\n")
                    write(f.body)
                    decrementIndentation()
                    write("\n}")
                }
            }
            decrementIndentation()
            write("\n}\n")
            flush()
            close()
        }
    }

    abstract fun fillMemberList(members: MutableList<ClassMember>): Unit
    abstract fun fillFunctionList(members: MutableList<ClassFunction>): Unit
}
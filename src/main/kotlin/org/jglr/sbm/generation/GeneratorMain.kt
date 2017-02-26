package org.jglr.sbm.generation

import java.io.File

object GeneratorMain {
    @JvmStatic
    fun main(args: Array<String>) {
        SPIRVOpcodesGenerator.generate(File("./src/main/gen/org/jglr/sbm", "Opcodes.java"))
        SPIRVTypeVisitorGenerator.generate(File("./src/main/gen/org/jglr/sbm/visitors", "TypeVisitor.java"))
        SPIRVFunctionVisitorGenerator.generate(File("./src/main/gen/org/jglr/sbm/visitors", "FunctionVisitor.java"))
        SPIRVCodeWriterGenerator.generate(File("./src/main/gen/org/jglr/sbm/visitors", "CodeWriter.java"))
    }
}
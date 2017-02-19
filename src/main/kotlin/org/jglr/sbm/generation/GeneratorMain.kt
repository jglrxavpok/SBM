package org.jglr.sbm.generation

import java.io.File

object GeneratorMain {
    @JvmStatic
    fun main(args: Array<String>) {
        SPIRVOpcodes.generate(File("./src/main/gen/org/jglr/sbm", "Opcodes.java"))
    }
}
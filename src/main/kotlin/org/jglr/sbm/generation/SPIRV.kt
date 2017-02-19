package org.jglr.sbm.generation

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

private fun parse(inputStream: InputStream): JsonObject {
    return Parser().parse(inputStream) as JsonObject
}

private fun select(defaultPath: String, filename: String): InputStream {
    if(File(defaultPath, filename).exists()) {
        return FileInputStream(File(defaultPath, filename))
    } else {
        return JsonObject::class.java.getResourceAsStream("/"+filename)
    }
}

private val defaultPath = System.getenv("VK_SDK_PATH") + "/include/vulkan"
val SPIRVData = parse(select(defaultPath, "spirv.json"))
val SPIRVGrammar = parse(select(defaultPath, "spirv.core.grammar.json"))
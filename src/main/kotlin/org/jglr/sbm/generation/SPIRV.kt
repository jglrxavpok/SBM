package org.jglr.sbm.generation

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

private fun parse(inputStream: InputStream): Any? {
    return Parser().parse(inputStream)
}

private fun select(defaultPath: String, filename: String): InputStream {
    if(File(defaultPath, filename).exists()) {
        return FileInputStream(File(defaultPath, filename))
    } else {
        return JsonObject::class.java.getResourceAsStream("/"+filename)
    }
}

private val defaultPath = System.getenv("VK_SDK_PATH") + "/include/vulkan"
val SPIRVData = parse(select(defaultPath, "spirv.json")) as JsonObject
@Suppress("UNCHECKED_CAST")
val SPIRVOpcodes = parse(select(defaultPath, "spirv-opcodesonly.json")) as JsonArray<JsonObject>
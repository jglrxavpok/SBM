package org.jglr.sbm.generation

data class ClassMember(val name: String, val type: String, val value: String? = null, val access: MemberAccess = MemberAccess.PUBLIC) {
    var documentation: String? = null
}

data class ClassFunction(val name: String, val returnType: String, val argumentNames: List<String>, val argumentTypes: List<String>,
                         val body: String,
                         val static: Boolean = false,
                         val access: MemberAccess = MemberAccess.PUBLIC) {
    var documentation: String? = null
    var bodyless: Boolean = false
    var exceptions = emptyList<String>()
    var annotations = emptyList<String>()
}

fun BodylessClassFunction(name: String, returnType: String, argumentNames: List<String>, argumentTypes: List<String>, static: Boolean = false, access: MemberAccess = MemberAccess.PUBLIC) =
        ClassFunction(name, returnType, argumentNames, argumentTypes, "", static, access).apply { bodyless = true }

enum class MemberAccess {
    PUBLIC, PRIVATE, PROTECTED
}


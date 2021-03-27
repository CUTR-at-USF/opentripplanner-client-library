package edu.usf.cutr.otp.serverinfo.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class ServerInfo (
    var serverVersion: ServerVersion? = null,
    var cpuName: String? = null,
    var nCores: Int? = null ,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)

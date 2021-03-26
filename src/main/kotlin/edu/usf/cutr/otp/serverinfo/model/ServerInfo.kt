package edu.usf.cutr.otp.serverinfo.model

import java.util.*

data class ServerInfo (
    var serverVersion: ServerVersion? = null,
    var cpuName: String? = null,
    var nCores: Int? = null ,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)

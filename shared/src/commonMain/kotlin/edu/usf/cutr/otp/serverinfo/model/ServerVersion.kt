package edu.usf.cutr.otp.serverinfo.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class ServerVersion (
    var version: String? = null,
    var major: Int? = null,
    var minor: Int? = null,
    var incremental: Int? = null,
    var qualifier: String? = null,
    var commit: String? = null,
    var branch: String? = null,
    var describe: String? = null,
    var commitTime: String? = null,
    var buildTime: String? = null,
    var dirty: Boolean? = null,
    var longVersionString: String? = null,
    var shortVersionString: String? = null,
    var uid: Int? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
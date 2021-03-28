package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class To_(
    val name: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val arrival: Long? = null,
    val vertexType: String? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
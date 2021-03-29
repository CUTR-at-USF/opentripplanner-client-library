package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class From_
(
    val name: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val departure: Long? = null,
    val vertexType: String? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class From_
(
    val name: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val departure: Int? = null,
    val vertexType: String? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
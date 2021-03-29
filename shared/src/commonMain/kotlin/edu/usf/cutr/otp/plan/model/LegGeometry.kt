package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class LegGeometry
(
    val points: String? = null,
    val length: Int? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
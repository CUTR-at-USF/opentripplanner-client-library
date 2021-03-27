package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Details
(
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable


@Serializable
data class Metadata
(
    val searchWindowUsed: Int? = null,
    val nextDateTime: Int? = null,
    val prevDateTime: Int? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
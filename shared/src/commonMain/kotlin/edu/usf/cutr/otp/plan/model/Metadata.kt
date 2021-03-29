package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable


@Serializable
data class Metadata
(
    val searchWindowUsed: Int? = null,
    val nextDateTime: Long? = null,
    val prevDateTime: Long? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
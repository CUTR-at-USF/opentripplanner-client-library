package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Regular(
    val currency: Currency? = null,
    val cents: Int? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
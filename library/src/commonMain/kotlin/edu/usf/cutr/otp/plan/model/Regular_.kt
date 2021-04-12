package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Regular_(
    val fareId: String? = null,
    val price: Price? = null,
    val routes: List<String>? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
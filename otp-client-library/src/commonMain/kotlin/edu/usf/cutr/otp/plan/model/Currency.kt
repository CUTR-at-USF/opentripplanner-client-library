package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Currency(
    val defaultFractionDigits: Int? = null,
    val currencyCode: String? = null,
    val currency: String? = null,
    val symbol: String? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
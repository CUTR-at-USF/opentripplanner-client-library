package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Fare_ (
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
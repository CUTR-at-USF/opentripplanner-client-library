package edu.usf.cutr.otp.planner.model.error

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Error
(
    val id: Int? = null,
    val message: String? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
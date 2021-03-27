package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Step
(
    val distance: Double? = null,
    val relativeDirection: String? = null,
    val streetName: String? = null,
    val absoluteDirection: String? = null,
    val stayOn: Boolean? = null,
    val area: Boolean? = null,
    val bogusName: Boolean? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val elevation: String? = null,
    val additionalProperties: Map<String,@Polymorphic Any> = HashMap(),
)
package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Itinerary (
    val duration: Int? = null,
    val startTime: Long? = null,
    val endTime: Long? = null,
    val walkTime: Int? = null,
    val transitTime: Int? = null,
    val waitingTime: Int? = null,
    val walkDistance: Double? = null,
    val walkLimitExceeded: Boolean? = null,
    val elevationLost: Double? = null,
    val elevationGained: Double? = null,
    val transfers: Int? = null,
    val fare: Fare? = null,
    val legs: List<Leg>? = null,
    val tooSloped: Boolean? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
package edu.usf.cutr.otp.planner.model


data class Itinerary (
    val duration: Int? = null,
    val startTime: Int? = null,
    val endTime: Int? = null,
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
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
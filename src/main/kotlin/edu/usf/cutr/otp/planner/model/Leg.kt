package edu.usf.cutr.otp.planner.model


data class Leg (
    val startTime: Int? = null,
    val endTime: Int? = null,
    val departureDelay: Int? = null,
    val arrivalDelay: Int? = null,
    val realTime: Boolean? = null,
    val distance: Double? = null,
    val pathway: Boolean? = null,
    val mode: String? = null,
    val transitLeg: Boolean? = null,
    val route: String? = null,
    val agencyTimeZoneOffset: Int? = null,
    val interlineWithPreviousLeg: Boolean? = null,
    val from: From_? = null,
    val to: To_? = null,
    val legGeometry: LegGeometry? = null,
    val steps: List<Step>? = null,
    val rentedBike: Boolean? = null,
    val duration: Double? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
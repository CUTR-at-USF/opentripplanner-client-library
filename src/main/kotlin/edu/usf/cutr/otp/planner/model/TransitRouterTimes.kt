package edu.usf.cutr.otp.planner.model


data class TransitRouterTimes
(
    val tripPatternFilterTime: Int? = null,
    val accessEgressTime: Int? = null,
    val raptorSearchTime: Int? = null,
    val itineraryCreationTime: Int? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
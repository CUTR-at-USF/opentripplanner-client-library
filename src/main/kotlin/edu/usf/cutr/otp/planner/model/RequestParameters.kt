package edu.usf.cutr.otp.planner.model

import edu.usf.cutr.otp.planner.model.core.OptimizeType
import java.util.*


data class RequestParameters
(
    val fromPlace: String? = null,
    val toPlace: String? = null,
    val alightSlack: Int? = null,
    val arriveBy: Boolean? = false,
    val optimizeType: OptimizeType? = OptimizeType.QUICK,
    val intermediatePlaces: List<String>? = null,
    val maxWalkDistance: Double? = Double.MAX_VALUE,
    val triangleTimeFactor: Double? = null,
    val triangleSlopeFactor: Double? = null,
    val triangleSafetyFactor: Double? = null,
    val wheelchair: Boolean? = false,
    val date: Date? = Date(),
    val numItineraries: Int? = 3,
    val preferredRoutes: List<String>? = null,
    val unpreferredRoutes: List<String>? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
package edu.usf.cutr.otp.planner.model

import edu.usf.cutr.otp.planner.model.core.OptimizeType
import edu.usf.cutr.otp.planner.model.core.TraverseModes
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class RequestParameters
(
    val fromPlace: String? = null,
    val toPlace: String? = null,
    val alightSlack: Int? = null,
    val arriveBy: Boolean? = false,
    val traverseModes: TraverseModes? = null,
    val optimizeType: OptimizeType? = OptimizeType.QUICK,
    val intermediatePlaces: List<String>? = null,
    val maxWalkDistance: Double? = Double.MAX_VALUE,
    val triangleTimeFactor: Double? = null,
    val triangleSlopeFactor: Double? = null,
    val triangleSafetyFactor: Double? = null,
    val wheelchair: Boolean? = false,
    val date: String? = null,
    val numItineraries: Int? = 3,
    val preferredRoutes: List<String>? = null,
    val unpreferredRoutes: List<String>? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
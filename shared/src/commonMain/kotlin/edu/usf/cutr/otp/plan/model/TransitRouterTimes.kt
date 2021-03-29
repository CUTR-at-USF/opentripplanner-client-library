package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class TransitRouterTimes
(
    val tripPatternFilterTime: Int? = null,
    val accessEgressTime: Int? = null,
    val raptorSearchTime: Int? = null,
    val itineraryCreationTime: Int? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
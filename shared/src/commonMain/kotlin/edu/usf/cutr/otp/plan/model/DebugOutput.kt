package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable


@Serializable
data class DebugOutput
(
    val precalculationTime: Int? = null,
    val directStreetRouterTime: Int? = null,
    val transitRouterTime: Int? = null,
    val filteringTime: Int? = null,
    val renderingTime: Int? = null,
    val totalTime: Int? = null,
    val transitRouterTimes: TransitRouterTimes? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
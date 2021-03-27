package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable


@Serializable
data class Plan
(
    val date: Int? = null,
    val from: From? = null,
    val to: To? = null,
    val itineraries: List<Itinerary>? = null,
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
)
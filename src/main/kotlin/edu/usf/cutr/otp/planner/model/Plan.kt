package edu.usf.cutr.otp.planner.model

import java.util.*


data class Plan
(
    val date: Int? = null,
    val from: From? = null,
    val to: To? = null,
    val itineraries: List<Itinerary>? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
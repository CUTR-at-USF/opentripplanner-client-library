package edu.usf.cutr.otp.planner.model

import java.util.*


data class LegGeometry
(
    val points: String? = null,
    val length: Int? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
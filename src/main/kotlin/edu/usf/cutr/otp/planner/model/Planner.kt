package edu.usf.cutr.otp.planner.model

import java.util.*


data class Planner
(
    val requestParameters: RequestParameters? = null,
    val plan: Plan? = null,
    val metadata: Metadata? = null,
    val debugOutput: DebugOutput? = null,
    val elevationMetadata: ElevationMetadata? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
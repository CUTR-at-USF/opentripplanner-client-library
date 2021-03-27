package edu.usf.cutr.otp.planner.model.error

import edu.usf.cutr.otp.planner.model.DebugOutput
import edu.usf.cutr.otp.planner.model.ElevationMetadata
import edu.usf.cutr.otp.planner.model.Plan
import edu.usf.cutr.otp.planner.model.RequestParameters
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Planner
(
    val requestParameters: RequestParameters? = null,
    val plan: Plan? = null,
    val error: Error? = null,
    val debugOutput: DebugOutput? = null,
    val elevationMetadata: ElevationMetadata? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
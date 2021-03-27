package edu.usf.cutr.otp.planner.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
class ElevationMetadata
(
    val ellipsoidToGeoidDifference: Double? = null,
    val geoidElevation: Boolean? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
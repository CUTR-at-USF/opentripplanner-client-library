package edu.usf.cutr.otp.planner.model


class ElevationMetadata
(
    val ellipsoidToGeoidDifference: Double? = null,
    val geoidElevation: Boolean? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
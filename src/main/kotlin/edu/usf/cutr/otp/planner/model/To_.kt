package edu.usf.cutr.otp.planner.model


data class To_(
    val name: String? = null,
    val lon: Double? = null,
    val lat: Double? = null,
    val arrival: Int? = null,
    val vertexType: String? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
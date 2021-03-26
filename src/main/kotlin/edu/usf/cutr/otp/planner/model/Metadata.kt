package edu.usf.cutr.otp.planner.model

import java.util.*


data class Metadata
(
    val searchWindowUsed: Int? = null,
    val nextDateTime: Int? = null,
    val prevDateTime: Int? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
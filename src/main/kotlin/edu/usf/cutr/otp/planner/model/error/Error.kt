package edu.usf.cutr.otp.planner.model.error

import java.util.*


data class Error
(
    val id: Int? = null,
    val message: String? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
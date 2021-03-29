package edu.usf.cutr.otp.plan.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
class Fare {
    val fare: Fare_? = null
    val details: Details? = null
    val additionalProperties: MutableMap<String,@Polymorphic Any> = HashMap()
}
package edu.usf.cutr.otp.bike_rental.model

import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
data class Stations (
    var stations: List<Station>? = null,
    val additionalProperties: MutableMap<String, @Polymorphic Any> = HashMap()
)
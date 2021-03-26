package edu.usf.cutr.otp.bike_rental.model

import java.util.*


data class Stations (
    var stations: List<Station>? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
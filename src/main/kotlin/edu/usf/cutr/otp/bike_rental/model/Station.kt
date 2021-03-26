package edu.usf.cutr.otp.bike_rental.model

import java.util.*

data class Station (
    var id: String? = null,
    var y: Double? = null,
    var x: Double? = null,
    var bikesAvailable: Int? = null,
    var spacesAvailable: Int? = null,
    var allowDropoff: Boolean? = null,
    var isFloatingBike: Boolean? = null,
    var isCarStation: Boolean? = null,
    var networks: List<String>? = null,
    var realTimeData: Boolean? = null,
    var name: String? = null,
    val additionalProperties: MutableMap<String, Any> = HashMap()
)
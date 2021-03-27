package edu.usf.cutr.otp.planner.model.core

import kotlinx.serialization.Serializable

@Serializable
enum class TraverseModes {
    WALK,
    TRANSIT,
    BICYCLE,
    BICYCLE_RENT,
    BICYCLE_PARK,
    CAR,
    CAR_PARK,
    TRAM,
    SUBWAY,
    RAIL,
    BUS,
    CABLE_CAR,
    FERRY,
    GONDOLA,
    FUNICULAR,
    AIRPLANE
}
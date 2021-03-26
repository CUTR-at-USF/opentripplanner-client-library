package edu.usf.cutr.otp.planner.model.core

enum class TraverseMode {
    WALK, BICYCLE, CAR, TRAM, SUBWAY, RAIL, BUS, FERRY, CABLE_CAR, GONDOLA, FUNICULAR, TRANSIT, TRAINISH, BUSISH, BOARDING, ALIGHTING, TRANSFER;

    val isTransit: Boolean
        get() = (this == TRAM) || (this == SUBWAY) || (this == RAIL) || (this == BUS) || (this == FERRY
                ) || (this == CABLE_CAR) || (this == GONDOLA) || (this == FUNICULAR) || (this == TRANSIT
                ) || (this == TRAINISH) || (this == BUSISH)
    val isOnStreetNonTransit: Boolean
        get() {
            return (this == WALK) || (this == BICYCLE) || (this == CAR)
        }
}
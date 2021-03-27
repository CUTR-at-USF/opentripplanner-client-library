package edu.usf.cutr.otp.planner.model.core

import kotlinx.serialization.Serializable

@Serializable
enum class OptimizeType {
    FLAT,
    GREENWAYS,
    QUICK,
    SAFE,
    TRANSFERS,
    TRIANGLE
}
package edu.usf.cutr.otp.plan.model.core

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
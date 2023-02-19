package com.lindenlabs.truckrouter.data.models

import kotlinx.serialization.Serializable

typealias RawShipment = String
typealias RawDriver = String

// todo remove default values
@Serializable
data class RawScheduleResponse(
    val shipments: List<RawShipment>,
    val drivers: List<RawDriver>
)
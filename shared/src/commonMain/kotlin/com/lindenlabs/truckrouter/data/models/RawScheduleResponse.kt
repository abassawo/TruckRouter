package com.lindenlabs.truckrouter.data.models

import kotlinx.serialization.Serializable

typealias RawShipment = String
typealias RawDriver = String

@Serializable
data class RawScheduleResponse(
    val shipments: List<RawShipment>,
    val drivers: List<RawDriver>
)
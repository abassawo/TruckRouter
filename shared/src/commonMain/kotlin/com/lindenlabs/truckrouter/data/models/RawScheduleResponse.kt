package com.lindenlabs.truckrouter.data.models

typealias RawShipment = String
typealias RawDriver = String

// todo remove default values
data class RawScheduleResponse(val shipments: List<RawShipment> = emptyList(), val drivers: List<RawDriver> = emptyList())
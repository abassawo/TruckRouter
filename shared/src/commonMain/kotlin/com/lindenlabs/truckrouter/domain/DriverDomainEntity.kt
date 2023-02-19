package com.lindenlabs.truckrouter.domain

data class ShipmentDomainEntity(val destination: String)

data class DriverDomainEntity(val name: String, val shipment: ShipmentDomainEntity, val suitabilityScore: Int = 0)
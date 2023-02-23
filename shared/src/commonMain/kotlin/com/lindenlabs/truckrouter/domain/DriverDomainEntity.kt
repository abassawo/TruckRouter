package com.lindenlabs.truckrouter.domain

data class Address(val text: String)
data class ShipmentDomainEntity(val fullAddress: Address)

data class DriverDomainEntity(
    val name: String,
    val shipment: ShipmentDomainEntity,
    val baseSuitabilityScore: Double,
    val suitabilityScore: Double = 1.5 * baseSuitabilityScore
)
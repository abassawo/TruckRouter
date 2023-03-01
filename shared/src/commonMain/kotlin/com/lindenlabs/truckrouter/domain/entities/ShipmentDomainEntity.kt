package com.lindenlabs.truckrouter.domain.entities

data class ShipmentDomainEntity(val address: Address)

data class Address(
    val fullAddressText: String,
    val streetName: String,
    val streetNameClass: NameClassification = streetName.toClassification()
)


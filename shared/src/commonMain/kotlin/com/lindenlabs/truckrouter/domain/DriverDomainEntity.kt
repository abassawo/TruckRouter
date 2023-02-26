package com.lindenlabs.truckrouter.domain

enum class NameClassification {
    OddLength,
    EvenLength
}

data class Address(
    val fullAddressText: String,
    val streetName: String,
    val streetNameClass: NameClassification = streetName.toClassification(

    )
)

data class ShipmentDomainEntity(val address: Address)

data class DriverDomainEntity(
    val name: String,
    val nameClassification: NameClassification = name.toClassification()
)

data class Score(
    val baseScore: Double,
    private val multiplier: Double,
    val totalScore: Double = baseScore * multiplier
)

fun String.isEvenLength() = length % 2 == 0

fun String.toClassification() =
    if (isEvenLength()) NameClassification.EvenLength else NameClassification.OddLength
package com.lindenlabs.truckrouter.domain.entities

enum class NameClassification {
    OddLength,
    EvenLength
}

data class DriverDomainEntity(
    val name: String,
    val nameClassification: NameClassification = name.toClassification()
)

data class Score(
    val baseScore: Double,
    val multiplier: Double,
    val totalScore: Double = baseScore * multiplier
)

fun String.isEvenLength() = length % 2 == 0

fun String.toClassification() =
    if (isEvenLength()) NameClassification.EvenLength else NameClassification.OddLength
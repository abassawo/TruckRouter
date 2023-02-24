package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawDriver

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

data class ShipmentDomainEntity(val address: Address) {

    fun toSuitabilityScores(drivers: List<DriverDomainEntity>): List<DriverDomainEntity> {
        return drivers.map {
//            val score = SuitabilityScorer().score(it.name, this.address.streetName)
            DriverDomainEntity(it.name)
        }.sortedByDescending {
            SuitabilityScorer().score(it.name, this.address.streetName).totalScore
        }
    }

    fun findDriver(drivers: List<DriverDomainEntity>): DriverDomainEntity? {
        val results = toSuitabilityScores(drivers)
        if(results.isEmpty()) return null
        val result: DriverDomainEntity = results.first()
        println("top driver for $this is $result")
        return results.filter { it.destination == null }.first().also {
            it.destination = this.address
        }
    }

    var driver: DriverDomainEntity? = null
}

data class DriverDomainEntity(
    val name: String,
    val nameClassification: NameClassification = name.toClassification()
) {
    var destination: Address? = null
}

data class Score(
    private val baseScore: Double,
    private val multiplier: Double,
    val totalScore: Double = baseScore * multiplier
)

fun String.isEvenLength() = length % 2 == 0

fun String.toClassification() =
    if (isEvenLength()) NameClassification.EvenLength else NameClassification.OddLength
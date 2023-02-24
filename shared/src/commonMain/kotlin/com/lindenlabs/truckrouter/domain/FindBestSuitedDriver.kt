package com.lindenlabs.truckrouter.domain

class FindBestSuitedDriver(private val scorer: SuitabilityScorer = SuitabilityScorer()) {
    operator fun invoke(
        destination: ShipmentDomainEntity,
        drivers: MutableList<DriverDomainEntity>
    ): DriverDomainEntity? = destination.toSuitabilityScores(drivers).firstOrNull()

    private fun ShipmentDomainEntity.toSuitabilityScores(drivers: List<DriverDomainEntity>): List<DriverDomainEntity> =
        drivers
            .map { DriverDomainEntity(it.name) }
            .sortedByDescending {
                scorer.score(it.name, address.streetName).totalScore
            }

}
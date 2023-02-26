package com.lindenlabs.truckrouter.domain

interface ScheduleMatcher {
    operator fun invoke(
        destination: ShipmentDomainEntity,
        drivers: MutableList<DriverDomainEntity>
    ): DriverDomainEntity?
}
class FindBestSuitedDriver(private val scorer: SuitabilityScorer = SuitabilityScorer()) : ScheduleMatcher {
    override operator fun invoke(
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
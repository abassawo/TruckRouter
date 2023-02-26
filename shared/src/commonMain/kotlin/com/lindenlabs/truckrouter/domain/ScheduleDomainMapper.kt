package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

class ScheduleDomainMapper(
    private val streetNameExtractor: ExtractStreetName = StreetNameExtractor(),
    private val findBestSuitedDriver: ScheduleMatcher = FindBestSuitedDriver()
) {

    operator fun invoke(rawScheduleResponse: RawScheduleResponse): ScheduleDomainEntity =
        rawScheduleResponse.mappedToMaximizeSuitabilityScore()

    private fun RawScheduleResponse.mappedToMaximizeSuitabilityScore(): ScheduleDomainEntity {
        val scheduleAsMap: MutableMap<DriverDomainEntity, ShipmentDomainEntity> = mutableMapOf()
        val availableDrivers = drivers.toSortedDriverEntities().toMutableList()

        for (shipmentDomainEntity in shipments.toSortedDestinationEntities()) {
            shipmentDomainEntity.findBestMatch(availableDrivers)?.let { bestSuitedDriver ->
                scheduleAsMap[bestSuitedDriver] = shipmentDomainEntity
                availableDrivers.remove(bestSuitedDriver)
            }
        }
        return scheduleAsMap
    }

    fun ShipmentDomainEntity.findBestMatch(availableDrivers: MutableList<DriverDomainEntity>): DriverDomainEntity? =
        findBestSuitedDriver(this, availableDrivers)

    private fun List<String>.toSortedDriverEntities(): MutableList<DriverDomainEntity> =
        sortedByDescending { it.length }
            .map { DriverDomainEntity(it) }
            .toMutableList()

    private fun List<String>.toSortedDestinationEntities(): MutableList<ShipmentDomainEntity> =
        sortedByDescending { it.length }
            .map { address ->
                ShipmentDomainEntity(
                    address = Address(
                        fullAddressText = address,
                        streetName = address.toStreetName()
                    )
                )
            }
            .toMutableList()


    private fun String.toStreetName(): String = streetNameExtractor(this)

}


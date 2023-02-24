package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawDriver
import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.data.models.RawShipment

class ScheduleDomainMapper(
    private val streetNameExtractor: StreetNameExtractor = StreetNameExtractor(),
    private val suitabilityScorer: SuitabilityScorer = SuitabilityScorer()
) {

    private val map: MutableMap<DriverDomainEntity, ShipmentDomainEntity> =
        mutableMapOf()

    operator fun invoke(rawScheduleResponse: RawScheduleResponse): ScheduleDomainEntity {
        val matching = rawScheduleResponse.mappedToMaximizeSuitabilityScore()
        return matching.keys // todo
    }

    private fun RawScheduleResponse.mappedToMaximizeSuitabilityScore(): Map<DriverDomainEntity, ShipmentDomainEntity> {
        //Given rules of suitability scores, assign drivers to shipment so Total Suitability Score for ALL is maxed
        val raw = this
        map.clear()
        val shipmentsSorted =  raw.shipments.sortedByDescending { it.length }.toMutableList()

        val finalMap: MutableMap<DriverDomainEntity, MutableList<ShipmentDomainEntity>> = mutableMapOf()
        val mapOfDestinationsToDrivers = mutableMapOf<ShipmentDomainEntity, Pair<DriverDomainEntity, Double>>()

        raw.shipments.sortedByDescending { it.length } // todo - sort by drivers
        val allDrivers: MutableList<DriverDomainEntity> = raw.drivers.sortedByDescending { it.length }.map { DriverDomainEntity(it, ) }.toMutableList()
        allDrivers.sortedByDescending { it.name.length }.forEachIndexed { driverIndex, driver ->
//            var index = 0
            var iterator = shipmentsSorted.iterator()
            while(iterator.hasNext()) {
                val shipment: RawShipment = iterator.next()
                val streetName = streetNameExtractor(shipment)
                val address = Address(
                    fullAddressText = shipment,
                    streetName = streetName
                )

                val shipmentDomainEntity = ShipmentDomainEntity(address)
//                , score = suitabilityScorer.score(
//                    driverName = driver,
//                    streetName = streetName
//                ))
                shipmentDomainEntity.findDriver(allDrivers)?.let {
                    shipmentDomainEntity.driver = it
                    allDrivers.remove(it)
                    map[it] = shipmentDomainEntity
                }

            }

        }
        return map
    }
}
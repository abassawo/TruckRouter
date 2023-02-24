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
                }

//                index++
            }
//            shipments.forEachIndexed { shipmentIndex, shipment1 ->
//                val streetName = streetNameExtractor(shipmentsSorted[shipmentIndex])
//                val address = Address(
//                    fullAddressText = shipmentsSorted[shipmentIndex],
//                    streetName = streetName
//                )
//
//                if(streetName.length == driver.length) {
//
//                }
//
//                val score = suitabilityScorer.score(
//                    driverName = driver,
//                    streetName = streetName
//                )
//
//                val shipment = ShipmentDomainEntity(address, score)
//                val potentialMatchup = DriverDomainEntity(
//                    name = driver
//                )
////                if(map[potentialMatchup]?.score?.totalScore ?: 0.0 < shipment.score.totalScore) {
//                    // check that no other key already has this field as its destination and with a higher sc
//                    val hasCompetition = map.entries.find { it.key != potentialMatchup && it.value.address == shipment.address
//                            && it.value.score.totalScore > shipment.score.totalScore } != null
////                    if(hasCompetition.not()) {
//                        map[potentialMatchup] = shipment
//                        val list = finalMap[potentialMatchup] ?: mutableListOf()
//                        list.add(shipment)
//                        shipmentsSorted.remove(shipmentsSorted[shipmentIndex])
//                        finalMap[potentialMatchup] = list
//                        mapOfDestinationsToDrivers[shipment] = potentialMatchup to  shipment.score.totalScore
////                    }
//
//
////                }
////                map[potentialMatchup] = shipmentsSorted.takeIf { it > map[potentialMatchup] && map. }
////                    shipments.sortedByDescending { it.score.totalScore }.toMutableList()
//            }
        }
        println("Set size ${map.toList().size}")
        println("Set size ${finalMap.toList().size}")
        var index = 0


        while(index < map.entries.size) {
            val key = map.entries.toList()[index].key
            val value = map.entries.toList()[index].value


            index++
        }
        for(entry in finalMap.entries) {
            println("Final map: " + entry.key + " " + entry.value)
        }

//        map.entries.forEachIndexed { index, entry ->
//            val values = entry.value
//
//
//            map[k]
//        }
        return map
    }
}
package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

class ScheduleDomainMapper(
    private val streetNameExtractor: StreetNameExtractor = StreetNameExtractor(),
    private val suitabilityScorer: SuitabilityScorer = SuitabilityScorer()
) {
    operator fun invoke(rawScheduleResponse: RawScheduleResponse): ScheduleDomainEntity {
        return rawScheduleResponse.mappedToMaximizeSuitabilityScore()
    }

    private fun RawScheduleResponse.mappedToMaximizeSuitabilityScore(): ScheduleDomainEntity {
        //Given rules of suitability scores, assign drivers to shipment so Total Suitability Score for ALL is maxed
        val raw = this
        raw.shipments.sortedBy { it } // todo - sort by drivers
        return buildSet {
            for ((index, driver) in raw.drivers.withIndex()) {
                val fullAddress = Address(raw.shipments[index])
                val streetName = streetNameExtractor.invoke(fullAddress.text)

                val potentialMatchup = DriverDomainEntity(
                    name = driver,
                    shipment = ShipmentDomainEntity(fullAddress),
                    baseSuitabilityScore = suitabilityScorer.score(
                        driverName = driver,
                        streetName = streetName
                    )
                )
                println("Street name $streetName") // todo - see if its the max for that driver
                println("Potential matchup $potentialMatchup") // todo - see if its the max for that driver
                add(potentialMatchup)
            }
        }
    }
}
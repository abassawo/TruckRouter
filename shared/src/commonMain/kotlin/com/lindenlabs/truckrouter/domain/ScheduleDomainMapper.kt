package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

class ScheduleDomainMapper {
    private val driverDestinations = mutableSetOf<DriverDomainEntity>()
    operator fun invoke(rawScheduleResponse: RawScheduleResponse) : ScheduleDomainEntity {
        return rawScheduleResponse.mappedToMaximizeSuitabiltiyScore()
    }

    private fun RawScheduleResponse.mappedToMaximizeSuitabiltiyScore(): ScheduleDomainEntity {
        //Given rules of suitability scores, assign drivers to shipment so Total Suitability Score for ALL is maxed
        val raw = this
        raw.shipments.sortedBy { it } // todo - sort by drivers
        return buildSet {
            for((index, driver) in raw.drivers.withIndex()) {
                add(DriverDomainEntity(
                    name = driver,
                    shipment = ShipmentDomainEntity(raw.shipments[index])
                )) // todo - sort in order so driver is maxed
            }
        }
    }
}
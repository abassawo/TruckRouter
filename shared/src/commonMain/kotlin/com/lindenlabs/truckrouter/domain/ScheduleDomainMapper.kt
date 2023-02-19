package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

class ScheduleDomainMapper {
    private val driverDestinations = mutableSetOf<DriverDomainEntity>()
    operator fun invoke(rawScheduleResponse: RawScheduleResponse) : ScheduleDomainEntity {
        return rawScheduleResponse.mappedToMaximizeSuitabiltiyScore()
    }

    private fun RawScheduleResponse.mappedToMaximizeSuitabiltiyScore(): ScheduleDomainEntity {
        // What exactly does maximize over the set of drivers mean
        //Given rules of suitabiltiy scores, assign drivers to shipment
        return emptyList()
    }
}
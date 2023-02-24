package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.Platform


class GetScheduleDomainEntity(private val platform: Platform, private val mapper: ScheduleDomainMapper) {

    suspend operator fun invoke(): Map<DriverDomainEntity, ShipmentDomainEntity> {
        val rawSchedule = platform.appDataSource.getDailySchedule()
        return mapper.invoke(rawSchedule)
    }
}
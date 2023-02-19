package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.Platform


class GetScheduleDomainEntity(private val platform: Platform, private val mapper: ScheduleDomainMapper) {

    suspend operator fun invoke(): ScheduleDomainEntity {
        val rawSchedule = platform.appDataSource.getDailySchedule()
        return mapper.map(rawSchedule)
    }
}
package com.lindenlabs.truckrouter.domain

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

class ScheduleDomainMapper {

    suspend fun map(rawScheduleResponse: RawScheduleResponse) : ScheduleDomainEntity {
        return ScheduleDomainEntity(emptyList()) // todo remove empty list
    }
}
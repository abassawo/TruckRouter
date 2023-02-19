package com.lindenlabs.truckrouter.data

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse

interface AppDataSource {
    suspend fun getDailySchedule() : RawScheduleResponse
}
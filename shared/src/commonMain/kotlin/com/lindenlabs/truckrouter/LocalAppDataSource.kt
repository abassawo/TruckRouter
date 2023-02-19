package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource
import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LocalAppDataSource(private val resourceReader: ResourceReader) : AppDataSource {

    override suspend fun getDailySchedule(): RawScheduleResponse =
        Json.decodeFromString(resourceReader())
}
package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource
import com.lindenlabs.truckrouter.domain.ScheduleDomainMapper

class AndroidPlatform(override val rawJson: String) : Platform {
    override val appDataSource: AppDataSource
        get() = LocalAppDataSource(this)
}
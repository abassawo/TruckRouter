package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource

class IOSPlatform(override val resourceReader: ResourceReader) : Platform {
    override val appDataSource: AppDataSource
        get() = LocalAppDataSource(this)
}
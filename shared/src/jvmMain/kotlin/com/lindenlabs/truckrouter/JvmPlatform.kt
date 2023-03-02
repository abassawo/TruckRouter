package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource

class JvmPlatform : Platform {
    override val appDataSource: AppDataSource
        get() = LocalAppDataSource(resourceReader = ResourceReader())
}
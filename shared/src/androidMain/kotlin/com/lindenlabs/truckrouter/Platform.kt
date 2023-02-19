package com.lindenlabs.truckrouter

import android.content.Context
import com.lindenlabs.truckrouter.data.AppDataSource

class AndroidPlatform(private val context: Context) : Platform {
    override val appDataSource: AppDataSource
        get() = LocalAppDataSource(resourceReader = ResourceReader(context))
}
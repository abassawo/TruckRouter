package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource

interface Platform {
    val appDataSource: AppDataSource
}
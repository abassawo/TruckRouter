package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.AppDataSource

interface Platform {
    val rawJson: String
    val appDataSource: AppDataSource
}
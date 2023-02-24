package com.lindenlabs.truckrouter.presentation

import kotlinx.datetime.LocalDateTime

data class ScheduleViewEntity(
    val driverName: String,
    val destinationAddress: String,
    val score: Double,
    val date: LocalDateTime,
)

data class HomeViewEntity(
    val totalSuitability: Double,
    val schedules: List<ScheduleViewEntity>,
    val headerText: String
)
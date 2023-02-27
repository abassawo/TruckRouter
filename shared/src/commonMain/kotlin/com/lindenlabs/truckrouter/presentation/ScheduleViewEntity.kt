package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.DateTimeUtil
import kotlinx.datetime.LocalDateTime

data class ScheduleViewEntity(
    val driverName: String,
    val destinationAddress: String,
    val score: Double,
    val date: LocalDateTime,
    val formattedDate: String = DateTimeUtil.formatDate(date).split(",").first(),
    val markerText: String = "$destinationAddress\nSuitability score: $score"
)

data class HomeViewEntity(
    val totalSuitability: Double,
    val schedules: List<ScheduleViewEntity>,
    val headerText: String,
    var selectedIndex: Int = 0,
    val highlightSelected: Boolean = false,
    val maxCardWidth: Float = 1f,
) {
    fun getSelectedSchedule() = schedules[selectedIndex]
}
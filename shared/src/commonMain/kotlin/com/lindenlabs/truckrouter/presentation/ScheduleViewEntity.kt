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

sealed class HomeViewEntity(open val maxCardWidth: Float = 1f) {
    data class Content(
        val totalSuitability: Double,
        val schedules: List<ScheduleViewEntity>,
        val headerText: String,
        var selectedIndex: Int,
        val highlightSelected: Boolean = false,
        override val maxCardWidth: Float = 1f
    ) : HomeViewEntity() {
        fun getSelectedSchedule() = schedules[selectedIndex]
    }

    object Error : HomeViewEntity()

    object Loading : HomeViewEntity()

}

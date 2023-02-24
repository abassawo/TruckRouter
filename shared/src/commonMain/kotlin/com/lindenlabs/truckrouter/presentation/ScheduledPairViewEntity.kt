package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.DateTimeUtil
import com.lindenlabs.truckrouter.domain.Score
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import kotlinx.datetime.LocalDateTime

data class ScheduledPairViewEntity(
    val driverName: String,
    val destinationAddress: String,
    val score: Double,
    val date: LocalDateTime = DateTimeUtil.now()
)

data class HomeViewEntity(val totalSuitability: Double, val schedule: List<ScheduledPairViewEntity>)
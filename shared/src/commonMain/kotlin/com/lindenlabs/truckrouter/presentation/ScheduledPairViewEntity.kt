package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.Score
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity

data class ScheduledPairViewEntity(
    val driverDomainEntity: DriverDomainEntity,
    val shipmentDomainEntity: ShipmentDomainEntity,
    val score: Score
)
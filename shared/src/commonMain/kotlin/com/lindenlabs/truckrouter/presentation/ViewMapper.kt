package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.DateTimeUtil
import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import com.lindenlabs.truckrouter.domain.SuitabilityScorer

class ViewMapper(private val scorer: SuitabilityScorer = SuitabilityScorer()) {

    fun map(domainEntityMap: Map<DriverDomainEntity, ShipmentDomainEntity>): HomeViewEntity {
        val schedules = domainEntityMap.entries.map {
            val score = scorer.score(it.key.name, it.value.address.streetName)
            ScheduleViewEntity(
                driverName = it.key.name,
                destinationAddress = it.value.address.fullAddressText,
                score = score.totalScore,
                date = DateTimeUtil.now(),
            )
        }.sortedByDescending { it.score }


        return HomeViewEntity(
            schedules = schedules,
            totalSuitability = schedules.sumOf { it.score },
            headerText = "All Drivers: ${schedules.first().formattedDate}",
            selectedIndex = 0
        )
    }
}



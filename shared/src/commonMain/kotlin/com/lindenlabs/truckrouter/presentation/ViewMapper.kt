package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.DateTimeUtil
import com.lindenlabs.truckrouter.domain.SuitabilityScorer
import com.lindenlabs.truckrouter.domain.SuitableMatchDomainEntity

class ViewMapper(private val scorer: SuitabilityScorer = SuitabilityScorer()) {

    fun map(matches: List<SuitableMatchDomainEntity>): HomeViewEntity {

        val schedules = matches.map { pair ->
            val driver = pair.first
            val shipment = pair.second
            val score = scorer.score(driver.name, shipment.address.streetName)
            ScheduleViewEntity(
                driverName = driver.name,
                destinationAddress = shipment.address.fullAddressText,
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



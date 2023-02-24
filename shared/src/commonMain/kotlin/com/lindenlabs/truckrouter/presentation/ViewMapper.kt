package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.DateTimeUtil
import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import com.lindenlabs.truckrouter.domain.SuitabilityScorer
import kotlinx.datetime.LocalDateTime

class ViewMapper(private val scorer: SuitabilityScorer = SuitabilityScorer()) {

    fun map(domainEntityMap: Map<DriverDomainEntity, ShipmentDomainEntity>): HomeViewEntity {
        val date = DateTimeUtil.now()
        val schedules = domainEntityMap.entries.map {
            val score = scorer.score(it.key.name, it.value.address.streetName)
            ScheduleViewEntity(
                driverName = it.key.name,
                destinationAddress = it.value.address.fullAddressText,
                score = score.totalScore,
                date = date,
            )
        }.sortedByDescending { it.score }


        return HomeViewEntity(
            schedules = schedules,
            totalSuitability = schedules.sumOf { it.score },
            headerText = date.toHomeScreenHeader()
        )
    }

    private fun LocalDateTime.toHomeScreenHeader(): String {
        return DateTimeUtil.formatDate(this)
            .split(",").first().let {
                "All Drivers: $it"
            }
    }
}



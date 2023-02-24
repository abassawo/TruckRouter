package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import com.lindenlabs.truckrouter.domain.SuitabilityScorer

class ViewMapper(val scorer: SuitabilityScorer = SuitabilityScorer()) {

    fun map(domainEntityMap: Map<DriverDomainEntity, ShipmentDomainEntity>): HomeViewEntity {
        val schedule = domainEntityMap.entries.map {
            val score = scorer.score(it.key.name, it.value.address.streetName)
            ScheduledPairViewEntity(it.key.name, it.value.address.fullAddressText, score = score.totalScore)
        }

        val totalScore = schedule.sumOf { it.score }
        println("Total score $totalScore")
        return HomeViewEntity(schedule = schedule, totalSuitability = schedule.sumOf { it.score})
    }
 }
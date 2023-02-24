package com.lindenlabs.truckrouter.presentation

import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import com.lindenlabs.truckrouter.domain.SuitabilityScorer

class ViewMapper(val scorer: SuitabilityScorer = SuitabilityScorer()) {

    fun map(domainEntityMap: Map<DriverDomainEntity, ShipmentDomainEntity>): List<ScheduledPairViewEntity> {
        return domainEntityMap.entries.map {
            val score = scorer.score(it.key.name, it.value.address.streetName)
            ScheduledPairViewEntity(it.key, it.value, score = score)
        }
    }
 }
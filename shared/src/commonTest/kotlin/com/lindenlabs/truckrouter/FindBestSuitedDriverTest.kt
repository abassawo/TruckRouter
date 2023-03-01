package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.domain.Address
import com.lindenlabs.truckrouter.domain.DriverDomainEntity
import com.lindenlabs.truckrouter.domain.FindBestSuitedDriver
import com.lindenlabs.truckrouter.domain.ShipmentDomainEntity
import org.junit.Test
import kotlin.test.assertEquals

class FindBestSuitedDriverTest {
    private val underTest: FindBestSuitedDriver = FindBestSuitedDriver()

    @Test
    fun test() {
        val address1 = Address("200 Osinski St", "Osinski St")
        val drivers = mutableListOf("Everardo Welch", "Bruce Wayne", "AB").map { DriverDomainEntity(it) }.toMutableList()
        val bestMatch = underTest.invoke(
            ShipmentDomainEntity(address1),
            drivers
        )
        assertEquals(drivers.first(), bestMatch)
    }
}

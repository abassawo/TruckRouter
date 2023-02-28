package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.domain.*
import com.lindenlabs.truckrouter.testdata.TestGenerator
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleDomainMapperTest {
    private var streetNameExtractor = StreetNameExtractor()
    private val findBestSuitedDriver = FindBestSuitedDriver()
    private val underTest: ScheduleDomainMapper =
        ScheduleDomainMapper(streetNameExtractor, findBestSuitedDriver)

    @Test
    fun `test that ten matchable items are in sample result`() {
        val input = TestGenerator.sampleInputUnwrapped()
        val result = underTest.invoke(input)
        assertEquals(10, result.size)
    }

    @Test
    fun `test that no destinations are doubly matches`() {
        val input = TestGenerator.sampleInputUnwrapped()
        val result = underTest.invoke(input)
        val destinationsAsSet = result.values.toSet()
        assertEquals(destinationsAsSet.size, result.values.size)
    }

    @Test
    fun `test empty list of drivers and destinations yields empty map`() {
        val sample = RawScheduleResponse(emptyList(), emptyList())
        val expected = emptyMap<DriverDomainEntity, ShipmentDomainEntity>()
        val actual: Map<DriverDomainEntity, ShipmentDomainEntity> = underTest.invoke(sample)
        assertEquals(expected, actual)
    }
}
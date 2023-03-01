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
    fun `test that json with 10 drivers and 10 shipments yields 10 matches`() {
        val input = TestGenerator.sampleInputUnwrapped()
        val result = underTest.invoke(input)
        val expectedSize = 10
        assertEquals(expected = expectedSize, actual = result.size)
    }

    @Test
    fun `test that no destination is doubly matched`() {
        val input = TestGenerator.sampleInputUnwrapped()
        val result = underTest.invoke(input)
        val destinationsAsSet = result.values.toSet()
        assertEquals(expected = destinationsAsSet.size, actual = result.values.size)
    }

    @Test
    fun `test empty list of drivers and destinations yields empty map`() {
        val sample = RawScheduleResponse(emptyList(), emptyList())
        val expected = emptyMap<DriverDomainEntity, ShipmentDomainEntity>()
        val actual: Map<DriverDomainEntity, ShipmentDomainEntity> = underTest.invoke(sample)
        assertEquals(expected = expected, actual = actual)
    }
}
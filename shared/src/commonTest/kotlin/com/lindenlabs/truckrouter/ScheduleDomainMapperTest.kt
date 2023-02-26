package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.data.models.RawScheduleResponse
import com.lindenlabs.truckrouter.domain.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ScheduleDomainMapperTest {
    val arrangeBuilder = ArrangeBuilder()
    var streetNameExtractor = mockStreetNameExtractor()
    val findBestSuitedDriver = mockDriverMatcher()
    val underTest: ScheduleDomainMapper =
        ScheduleDomainMapper(streetNameExtractor, findBestSuitedDriver)

    @Test
    fun `test empty list of drivers and destinations yields empty map`() {
        val sample = RawScheduleResponse(emptyList(), emptyList())
        val expected = emptyMap<DriverDomainEntity, ShipmentDomainEntity>()
        val actual: Map<DriverDomainEntity, ShipmentDomainEntity> = underTest.invoke(sample)
        assertEquals(expected, actual)
    }

    inner class ArrangeBuilder {
        var streetNameExtractorResult: String = ""
        var matchedDriverResult: DriverDomainEntity? = null

        fun withStreetNameExtractionResult(result: String) = also {
            this.streetNameExtractorResult = result
        }

        fun withMatchedDriverResultd(driver: DriverDomainEntity) = also {
            this.matchedDriverResult = driver
        }
    }

    fun mockStreetNameExtractor() = object : ExtractStreetName {
        override fun invoke(address: String): String = arrangeBuilder.streetNameExtractorResult
    }

    fun mockDriverMatcher() = object : ScheduleMatcher {
        override fun invoke(
            destination: ShipmentDomainEntity,
            drivers: MutableList<DriverDomainEntity>
        ): DriverDomainEntity? = arrangeBuilder.matchedDriverResult
    }
}
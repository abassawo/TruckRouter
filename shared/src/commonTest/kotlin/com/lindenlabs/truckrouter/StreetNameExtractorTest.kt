package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.domain.StreetNameExtractor
import kotlin.test.Test
import kotlin.test.assertEquals

class StreetNameExtractorTest{
    val underTest: StreetNameExtractor = StreetNameExtractor()
    val arrangeBuilder = ArrangeBuilder()

    @Test
    fun `validate street name is extracted for plain old name`() {
        arrangeBuilder.withAddress("215 Osinski Manors")
        val expected = "Osinski Manors"
        val actual = underTest(address = arrangeBuilder.address)
        assertEquals(expected, actual)
    }

    inner class ArrangeBuilder {
        var address: String = ""

        fun withAddress(address: String) = also {
            this.address = address
        }
    }
}
package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.domain.StreetNameExtractor
import kotlin.test.Test
import kotlin.test.assertEquals

class StreetNameExtractorTest{
    val underTest: StreetNameExtractor = StreetNameExtractor()
    private val arrangeBuilder = ArrangeBuilder()

    @Test
    fun `validate street name is extracted for plain old address`() {
        arrangeBuilder.withAddress("215 Osinski Manors")
        val expected = "Osinski Manors"
        val actual = underTest(address = arrangeBuilder.address)
        assertEquals(expected, actual)
    }

    @Test
    fun `validate street name is extracted for address with apt`() {
        arrangeBuilder.withAddress("1797 Adolf Island Apt. 744")
        val expected = "Adolf Island"
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
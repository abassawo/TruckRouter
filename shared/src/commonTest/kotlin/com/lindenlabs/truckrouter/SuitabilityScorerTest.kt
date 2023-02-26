package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.domain.SuitabilityScorer
import kotlin.test.Test
import kotlin.test.assertEquals

class SuitabilityScorerTest{
    private val underTest: SuitabilityScorer = SuitabilityScorer()

    @Test
    fun `validate base score is accurately scored`() {
        // TODO - consider putting an explanation
        val baseScore = underTest.score("Everardo Welch", "215 Osinski Manors")
        assertEquals(6.0, baseScore.baseScore)
    }

    // TODO - consider adding some tests for premium scoring
}

package com.lindenlabs.truckrouter

import com.lindenlabs.truckrouter.domain.Multiplier
import com.lindenlabs.truckrouter.domain.SuitabilityScorer
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SuitabilityScorerTest{
    private val underTest: SuitabilityScorer = SuitabilityScorer()

    @Test
    fun `validate base score is accurately scored`() {
        // TODO - consider putting an explanation
        val baseScore = underTest.score("Everardo Welch", "Osinski Manors")
        assertEquals(6.0, baseScore.baseScore)
    }

    @Test
    fun `validate premium score is accurately scored for two strings of length 14`() {
        val score = underTest.score("Everardo Welch", "Osinski Manors")
        assertTrue { score.totalScore > score.baseScore }
        assertTrue { score.multiplier == 1.5 }
        assertTrue { score.totalScore / 1.5 == score.baseScore }
    }

    @Test
    fun `validate that a premium multiplier is applied for all eligible multipliers`() {
        val score = underTest.score("Everardo Welch ", "Osinski Manors ")
        assertTrue { score.totalScore > score.baseScore }
        assertTrue { score.multiplier > 1.0 }
        // satisfies all 3 conditions: same length, divisible by 3, divisible by 5
        assertTrue { score.multiplier == 1.5.pow(Multiplier.values().size.toDouble()) }
    }

    @Test
    fun `validate that 2 multpliers are applied`() {
        val score = underTest.score("Eve", "Osi")
        assertTrue { score.totalScore > score.baseScore }
        assertTrue { score.multiplier > 1.0 }
        // satisfies 2 conditions: same length, divisible by 3
        assertTrue { score.multiplier == 1.5.pow(2.0) }
    }

}

package com.lindenlabs.truckrouter.domain

class SuitabilityScorer {

    fun score(driverName: String, streetName: String): Score {
        println("Driver name $driverName Street name $streetName")
        val baseScore = calculateBaseScore(driverName, streetName)
        val multiplier = getPremiumMultipliers(driverName, streetName)
        return Score(baseScore, multiplier)
    }

    private fun calculateBaseScore(driverName: String, streetName: String): Double {
        val isEvenLengthStreetName = streetName.length % 2 == 0
        return when {
            isEvenLengthStreetName -> {
                val vowelCount = driverName.getVowelCount()
                vowelCount * 1.5
            }
            else -> driverName.getConsonantCount().toDouble()

        }
    }

    private fun getPremiumMultipliers(driverName: String, streetName: String): Double {
        val isOddStreetName = streetName.length % 2 != 0
        val isOddDriverName = driverName.length % 2 != 0
        return when {
            isOddStreetName && isOddDriverName -> 1.5 // TODO - What I am understanding step 3 of the algorithm to mean but want to confirm
            else -> 1.0
        }
    }

    companion object {

        private const val VOWELS: String = "aeiou"
        private fun Char.isConsonant(): Boolean {
            return this.isLetter() && VOWELS.contains(this).not()
        }

        private fun Char.isVowel() =
            this.isLetter() && VOWELS.contains(this)
    }
}

val vowels = "aeiou"
private fun String.getConsonantCount(): Int {
    var count = 0
    for(i in 0 until this.length) {
        val current = this[i]
        when {
            current.isLetter() && current.isVowel().not() -> count++
        }
    }
    return count
}

private fun Char.isVowel() = vowels.contains(this)

private fun String.getVowelCount(): Int {
    var count = 0
    for(i in 0 until this.length) {
        val current = this[i]
        when {
            current.isLetter() && current.isVowel() -> count++
        }
    }
    return count
}


package com.lindenlabs.truckrouter.domain


enum class Multiplier {
    SAME_LENGTH,
    DIVISIBLE_BY_THREE,
    DIVISIBLE_BY_FIVE;

    fun isAvailable(driverName: String, streetName: String): Boolean {
        return when(this) {
            SAME_LENGTH -> driverName.length == streetName.length
            DIVISIBLE_BY_THREE -> driverName.length % 3 == 0 && streetName.length % 3 == 0
            DIVISIBLE_BY_FIVE -> driverName.length % 5 == 0 && streetName.length % 5 == 0
        }
    }
}

class SuitabilityScorer {

    fun score(driverName: String, streetName: String): Score {
//        println("Driver name $driverName Street name $streetName")
        val baseScore = calculateBaseScore(driverName, streetName)
        var multiplierAmount = 1.0
        for(multiplier in Multiplier.values()) {
            if(multiplier.isAvailable(driverName = driverName, streetName = streetName)) {
                multiplierAmount *= 1.5
            }
        }
        return Score(baseScore, multiplierAmount)
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


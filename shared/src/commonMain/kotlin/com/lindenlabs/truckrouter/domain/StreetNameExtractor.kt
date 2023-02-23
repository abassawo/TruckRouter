package com.lindenlabs.truckrouter.domain

/*
   Street name extractor: assumes name are not numerical...
 */
class StreetNameExtractor {

    operator fun invoke(address: String): String =
        address
            .withoutAddressText()
            .withoutDigits()

    private fun String.withoutAddressText(): String {
        return this
            .replace("Suite", "")
            .replace("Apt.", "")
    }


    private fun String.withoutDigits(): String = also { original ->
        return buildString {
            for (character in original) {
                if (character.isDigit().not()) append(character)
            }
        }
    }
}
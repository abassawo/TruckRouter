package com.lindenlabs.truckrouter.domain

/*
   Street name extractor: assumes name are not numerical...
 */
class StreetNameExtractor {

    operator fun invoke(address: String): String =
        address
            .withoutDigits()
            .replace("Suite", "")
            .replace("Apt.", "")
    private fun String.withoutDigits(): String = filterNot { it.isDigit() }
}
package com.lindenlabs.truckrouter.domain

/*
   Street name extractor: assumes name are not numerical...
 */

interface ExtractStreetName {
    operator fun invoke(address: String): String
}

class StreetNameExtractor : ExtractStreetName {

    override operator fun invoke(address: String): String =
        address
            .withoutDigits()
            .replace("Suite", "")
            .replace("Apt.", "")
            .removePrefixSpaceIfAny()
            .removeSuffixSpaceIfAny()

    private fun String.withoutDigits(): String = filterNot { it.isDigit() }
}

private fun String.removePrefixSpaceIfAny(): String {
    return when {
        this.startsWith(" ") -> {
            return this.replaceFirstChar { "" }
        }
        else -> this
    }
}

private fun String.removeSuffixSpaceIfAny(): String {
    return when {
        this.endsWith(" ") -> {
            return this.replaceLastChar()
        }
        else -> this
    }
}

public inline fun String.replaceLastChar(): String {
    return this.dropLastWhile { it.isLetter().not() }
}


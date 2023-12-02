package fr.lidonis.adventofcode.y2022.day25

private const val SNAFU_DIGITS = "=-012"

private const val BASE = 5
private const val OFFSET = 2

class SpecialNumeralAnalogueFuelUnits {
    val stringValue: String
    val decimalValue: Long

    constructor(input: String) {
        stringValue = input
        decimalValue = input.toDecimal()
    }

    constructor(input: Long) {
        stringValue = input.toSnafu()
        decimalValue = input
    }

    private fun String.toDecimal() = fold(0L) { acc, c -> acc * BASE + snafuValue(c) }

    private fun snafuValue(c: Char) = SNAFU_DIGITS.indexOf(c) - OFFSET
    private fun snafuChar(i: Long) = SNAFU_DIGITS[i.toInt()]

    private fun Long.toSnafu(): String {
        if (this == 0L) return SNAFU_DIGITS[OFFSET].toString()
        var current = this
        return buildString {
            while (current > 0) {
                val offsetted = current + OFFSET
                current = offsetted / BASE
                append(snafuChar(offsetted % BASE))
            }
            reverse()
        }
    }
}

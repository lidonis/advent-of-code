package fr.lidonis.adventofcode.y2022.day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

internal class SpecialNumeralAnalogueFuelUnitsTest {

    @ParameterizedTest(name = "translate snafu {0} to {1}")
    @CsvFileSource(resources = ["/y2022/day25/snafu-to-decimal.txt"], numLinesToSkip = 1)
    fun snafuToDecimal(input: String, expected: String) {
        val snafu = SpecialNumeralAnalogueFuelUnits(input)
        assertThat(snafu.decimalValue).isEqualTo(expected.toLong())
    }

    @ParameterizedTest(name = "translate {1} to snafu {0}")
    @CsvFileSource(resources = ["/y2022/day25/snafu-to-decimal.txt"], numLinesToSkip = 1)
    fun decimalToSnafu(expected: String, input: String) {
        val snafu = SpecialNumeralAnalogueFuelUnits(input.toLong())
        assertThat(snafu.stringValue).isEqualTo(expected)
    }
}

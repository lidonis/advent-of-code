package fr.lidonis.adventofcode.y2021.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SubmarineReportTest {

    val example = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010""".trimIndent()

    @Test
    internal fun `power consumption`() {
        val submarineReport = SubmarineReport(example.lines())
        val powerConsumption = submarineReport.powerConsumption()
        assertThat(powerConsumption).isEqualTo(198)
    }

    @Test
    internal fun `life support rating`() {
        val submarineReport = SubmarineReport(example.lines())
        val lifeSupportRating = submarineReport.lifeSupportRating()
        assertThat(lifeSupportRating).isEqualTo(230)
    }
}

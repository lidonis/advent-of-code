package fr.lidonis.adventofcode.y2021.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SweepReportTest {

    val example = """
            199
            200
            208
            210
            200
            207
            240
            269
            260
            263
    """.trimIndent()
        .lines().map(String::toInt)

    @Test
    fun `count increase`() {
        val result = SweepReport(example).countIncrease()
        assertThat(result).isEqualTo(7)
    }

    @Test
    fun `count window increase`() {
        val result = SweepReport(example).countWindowIncrease()
        assertThat(result).isEqualTo(5)
    }
}

package fr.lidonis.adventofcode.y2024.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RedNosedReportsTest {

    private val input = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun part1() {
        val redNosedReports = RedNosedReports(input.lines())

        val result = redNosedReports.part1()
        assertThat(result).isEqualTo(2)
    }

    @Test
    fun part2() {
        val redNosedReports = RedNosedReports(input.lines())
        val result = redNosedReports.part2()
        assertThat(result).isEqualTo(4)
    }
}

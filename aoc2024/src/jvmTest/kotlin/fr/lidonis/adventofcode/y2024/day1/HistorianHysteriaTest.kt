package fr.lidonis.adventofcode.y2024.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HistorianHysteriaTest {

    private val input = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Test
    fun part1() {
        val historianHysteria = HistorianHysteria(input.lines())
        val result = historianHysteria.part1()
        assertThat(result).isEqualTo(11)
    }

    @Test
    fun part2() {
        val historianHysteria = HistorianHysteria(input.lines())
        val result = historianHysteria.part2()
        assertThat(result).isEqualTo(31)
    }
}

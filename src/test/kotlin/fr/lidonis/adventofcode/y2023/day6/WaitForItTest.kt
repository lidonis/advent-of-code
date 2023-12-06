package fr.lidonis.adventofcode.y2023.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WaitForItTest {

    val input = """
        Time:      7  15   30
        Distance:  9  40  200
    """.trimIndent()

    @Test
    fun part1() {
        val waitForIt = WaitForIt(input.lines())
        val result = waitForIt.part1()
        assertThat(result).isEqualTo(288)
    }

    @Test
    fun part2() {
        val waitForIt = WaitForIt(input.lines())
        val result = waitForIt.part2()
        assertThat(result).isEqualTo(71503)
    }
}

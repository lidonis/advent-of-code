package fr.lidonis.adventofcode.y2023.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TrebuchetTest {

    private val input1 = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()

    @Test
    fun part1() {
        val part1 = Trebuchet(input1.lines()).part1()
        assertThat(part1).isEqualTo(142)
    }

    private val input2 = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()

    @Test
    fun part2() {
        val part2 = Trebuchet(input2.lines()).part2()
        assertThat(part2).isEqualTo(281)
    }
}

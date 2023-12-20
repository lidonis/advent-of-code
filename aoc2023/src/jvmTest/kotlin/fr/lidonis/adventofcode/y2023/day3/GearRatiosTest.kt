package fr.lidonis.adventofcode.y2023.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GearRatiosTest {

    val input = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...${'$'}.*....
        .664.598..
    """.trimIndent()

    @Test
    fun part1() {
        val gearRatios = GearRatios(input.lines())
        val result = gearRatios.part1()
        assertThat(result).isEqualTo(4361)
    }

    @Test
    fun part2() {
        val gearRatios = GearRatios(input.lines())
        val result = gearRatios.part2()
        assertThat(result).isEqualTo(467835)
    }
}

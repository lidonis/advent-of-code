package fr.lidonis.adventofcode.y2023.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CubeConundrumTest {

    val input = """
        Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
        Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
        Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
        Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
        Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
    """.trimMargin()

    @Test
    fun part1() {
        val cubeConundrum = CubeConundrum(input.lines())
        val result = cubeConundrum.part1()
        assertThat(result).isEqualTo(8)
    }

    @Test
    fun part2() {
        val cubeConundrum = CubeConundrum(input.lines())
        val result = cubeConundrum.part2()
        assertThat(result).isEqualTo(2286)
    }
}

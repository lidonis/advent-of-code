package fr.lidonis.adventofcode.y2024.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GuardGallivantTest {

    private val input = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...
    """.trimIndent()

    @Test
    fun part1() {
        val guardGallivant = GuardGallivant(input.lines())
        val result = guardGallivant.part1()
        assertThat(result).isEqualTo(41)
    }

    @Test
    fun part2() {
        val guardGallivant = GuardGallivant(input.lines())
        val result = guardGallivant.part2()
        assertThat(result).isEqualTo(6)
    }
}

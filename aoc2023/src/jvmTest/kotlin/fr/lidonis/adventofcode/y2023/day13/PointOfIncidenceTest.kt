package fr.lidonis.adventofcode.y2023.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PointOfIncidenceTest {

    private val input = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.

        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
    """.trimIndent()

    @Test
    fun part1() {
        val pointOfIncidence = PointOfIncidence(input)
        val result = pointOfIncidence.part1()
        assertThat(result).isEqualTo(405)
    }

    @Test
    fun part2() {
        val pointOfIncidence = PointOfIncidence(input)
        val result = pointOfIncidence.part2()
        assertThat(result).isEqualTo(400)
    }
}

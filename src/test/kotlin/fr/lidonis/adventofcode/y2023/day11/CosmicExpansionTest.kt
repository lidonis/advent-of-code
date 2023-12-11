package fr.lidonis.adventofcode.y2023.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CosmicExpansionTest {

    private val input = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent()

    @Test
    fun part1() {
        val cosmicExpansion = CosmicExpansion(input.lines())
        val result = cosmicExpansion.part1()
        assertThat(result).isEqualTo(374)
    }

    @Test
    fun `part2 10`() {
        val cosmicExpansion = CosmicExpansion(input.lines())
        val result = cosmicExpansion.sumShortestPath(10)
        assertThat(result).isEqualTo(1030)
    }

    @Test
    fun `part2 100`() {
        val cosmicExpansion = CosmicExpansion(input.lines())
        val result = cosmicExpansion.sumShortestPath(100)
        assertThat(result).isEqualTo(8410)
    }
}

package fr.lidonis.adventofcode.y2023.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ClumsyCrucibleTest {

    private val input = """
        2413432311323
        3215453535623
        3255245654254
        3446585845452
        4546657867536
        1438598798454
        4457876987766
        3637877979653
        4654967986887
        4564679986453
        1224686865563
        2546548887735
        4322674655533
    """.trimIndent()

    @Test
    fun part1() {
        val clumsyCrucible = ClumsyCrucible(input.lines())
        val result = clumsyCrucible.part1()
        assertThat(result).isEqualTo(102)
    }

    @Test
    fun part2() {
        val clumsyCrucible = ClumsyCrucible(input.lines())
        val result = clumsyCrucible.part2()
        assertThat(result).isEqualTo(94)
    }
}

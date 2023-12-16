package fr.lidonis.adventofcode.y2023.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParabolicReflectorDishTest {

    private val input = """
        O....#....
        O.OO#....#
        .....##...
        OO.#O....O
        .O.....O#.
        O.#..O.#.#
        ..O..#O..O
        .......O..
        #....###..
        #OO..#....
    """.trimIndent()

    @Test
    fun part1() {
        val parabolicReflectorDish = ParabolicReflectorDish(input.lines())
        val result = parabolicReflectorDish.part1()
        assertThat(result).isEqualTo(136)
    }

    @Test
    fun part2() {
        val parabolicReflectorDish = ParabolicReflectorDish(input.lines())
        val result = parabolicReflectorDish.part2()
        assertThat(result).isEqualTo(64)
    }
}

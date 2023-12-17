package fr.lidonis.adventofcode.y2021.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChitonTest {

    private val input = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent()

    @Test
    fun part1() {
        val chiton = Chiton(input.lines())
        val result = chiton.part1()
        assertThat(result).isEqualTo(40)
    }

    @Test
    fun part2() {
        val chiton = Chiton(input.lines())
        val result = chiton.part2()
        assertThat(result).isEqualTo(315)
    }
}

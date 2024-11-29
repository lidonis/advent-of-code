package fr.lidonis.adventofcode.y2023.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class StepCounterTest {

    private val input = """
        ...........
        .....###.#.
        .###.##..#.
        ..#.#...#..
        ....#.#....
        .##..S####.
        .##..#...#.
        .......##..
        .##.#.####.
        .##..##.##.
        ...........
    """.trimIndent()

    @Disabled
    @Test
    fun part1() {
        val stepCounter = StepCounter(input.lines())
        val result = stepCounter.part1(6)
        assertThat(result).isEqualTo(16)
    }

    @Disabled
    @Test
    fun part2() {
        val stepCounter = StepCounter(input.lines())
        val result = stepCounter.part2()
        assertThat(result).isEqualTo(0)
    }
}

package fr.lidonis.adventofcode.y2020.day10

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day10.part1()).isEqualTo(1920)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day10.part2()).isEqualTo(1511207993344)
    }
}
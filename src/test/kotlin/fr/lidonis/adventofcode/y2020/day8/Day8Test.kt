package fr.lidonis.adventofcode.y2020.day8

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day8.part1()).isEqualTo(1384)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day8.part2()).isEqualTo(761)
    }
}
package fr.lidonis.adventofcode.y2020.day12

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day12.part1()).isEqualTo(441)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day12.part2()).isEqualTo(40014)
    }
}
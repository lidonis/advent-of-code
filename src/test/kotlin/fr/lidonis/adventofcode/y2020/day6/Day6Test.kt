package fr.lidonis.adventofcode.y2020.day6

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day6.part1()).isEqualTo(6885)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day6.part2()).isEqualTo(3550)
    }
}
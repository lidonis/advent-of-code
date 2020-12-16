package fr.lidonis.adventofcode.y2020.day11

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class
Day11Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day11.part1()).isEqualTo(2238)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day11.part2()).isEqualTo(2013)
    }
}
package fr.lidonis.adventofcode.y2020.day4

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day4.part1()).isEqualTo(208)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day4.part2()).isEqualTo(167)
    }
}
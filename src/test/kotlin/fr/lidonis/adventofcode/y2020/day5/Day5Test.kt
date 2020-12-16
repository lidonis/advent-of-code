package fr.lidonis.adventofcode.y2020.day5

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day5.part1()).isEqualTo(813)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day5.part2()).isEqualTo(612)
    }
}
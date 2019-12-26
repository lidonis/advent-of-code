package fr.lidonis.adventofcode.y2019.day3

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day3.part1()).isEqualTo(2180)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day3.part2()).isEqualTo(112316)
    }
}
package fr.lidonis.adventofcode.y2020.day7

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day7.part1()).isEqualTo(121)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day7.part2()).isEqualTo(3805)
    }
}
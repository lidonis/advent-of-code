package fr.lidonis.adventofcode.y2020.day2

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day2.part1()).isEqualTo(528)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day2.part2()).isEqualTo(497)
    }
}
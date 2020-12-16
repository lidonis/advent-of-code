package fr.lidonis.adventofcode.y2020.day1

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day1.part1()).isEqualTo(703131)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day1.part2()).isEqualTo(272423970)
    }
}
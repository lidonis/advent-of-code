package fr.lidonis.adventofcode.y2020.day9

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day9.part1()).isEqualTo(88311122)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day9.part2()).isEqualTo(13549369)
    }
}
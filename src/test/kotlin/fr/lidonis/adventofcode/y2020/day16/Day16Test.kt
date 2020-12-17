package fr.lidonis.adventofcode.y2020.day16

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day16.part1()).isEqualTo(19060)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day16.part2()).isEqualTo(953713095011)
    }
}

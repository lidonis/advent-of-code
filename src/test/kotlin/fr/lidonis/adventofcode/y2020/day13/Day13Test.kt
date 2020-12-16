package fr.lidonis.adventofcode.y2020.day13

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day13.part1()).isEqualTo(2935)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day13.part2()).isEqualTo(836024966345345)
    }
}

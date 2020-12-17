package fr.lidonis.adventofcode.y2020.day14

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun `part 1`() {
        Assertions.assertThat(Day14.part1()).isEqualTo(9879607673316)
    }

    @Test
    fun `part 2`() {
        Assertions.assertThat(Day14.part2()).isEqualTo(3435342392262)
    }
}

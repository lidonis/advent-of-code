package fr.lidonis.adventofcode.y2019.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `part 1`(){
        assertThat(Day4.part1()).isEqualTo(511)
    }

    @Test
    fun `part 2`(){
        assertThat(Day4.part2()).isEqualTo(316)
    }
}
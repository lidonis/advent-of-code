package fr.lidonis.adventofcode.y2019.day2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class Day2Test {

    @Test
    fun `part 1`(){
        assertThat(Day2.part1()).isEqualTo(4138658)
    }

    @Test
    fun `part 2`(){
        assertThat(Day2.part2()).isEqualTo(7264)
    }
}
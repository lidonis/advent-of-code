package fr.lidonis.adventofcode.y2019.day5

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun `part 1`(){
        Assertions.assertThat(Day5.part1()).isEqualTo(13547311)
    }

    @Test
    fun `part 2`(){
        Assertions.assertThat(Day5.part2()).isEqualTo(236453)
    }
}
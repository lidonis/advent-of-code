package fr.lidonis.adventofcode.y2019.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun part1() {
        assertThat(Day9.part1()).isEqualTo(3601950151)
    }

    @Test
    fun part2() {
        assertThat(Day9.part2()).isEqualTo(64236)
    }
}

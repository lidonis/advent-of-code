package fr.lidonis.adventofcode.y2019.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun `fuel requirements for mass of 12`() {
        val spacecraft = Spacecraft(listOf(12))
        assertThat(spacecraft.fuelRequirements()).isEqualTo(2)
    }

    @Test
    fun `fuel requirements for mass of 14`() {
        val spacecraft = Spacecraft(listOf(14))
        assertThat(spacecraft.fuelRequirements()).isEqualTo(2)
    }

    @Test
    fun `fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        assertThat(spacecraft.fuelRequirements()).isEqualTo(654)
    }

    @Test
    fun `fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        assertThat(spacecraft.fuelRequirements()).isEqualTo(33583)
    }

    @Test
    fun `fuel requirements for mass of 12 and 14`() {
        val spacecraft = Spacecraft(listOf(12, 14))
        assertThat(spacecraft.fuelRequirements()).isEqualTo(4)
    }

    @Test
    fun `full fuel requirements for mass of 1969`() {
        val spacecraft = Spacecraft(listOf(1969))
        assertThat(spacecraft.fullFuelRequirements()).isEqualTo(966)
    }

    @Test
    fun `part 1`() {
        assertThat(Day1.part1()).isEqualTo(3305301)
    }

    @Test
    fun `part 2`() {
        assertThat(Day1.part2()).isEqualTo(4955106)
    }

    @Test
    fun `full fuel requirements for mass of 100756`() {
        val spacecraft = Spacecraft(listOf(100756))
        assertThat(spacecraft.fullFuelRequirements()).isEqualTo(50346)
    }
}
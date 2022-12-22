package fr.lidonis.adventofcode.y2022.day19

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NotEnoughMineralsTest {
    val example = """
        Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
        Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.
    """.trimIndent().lines()

    @Test
    fun `quality level first blueprint`() {
        val result = NotEnoughMinerals(listOf(example[0])).sumOfQualityLevels()

        assertThat(result).isEqualTo(9)
    }

    @Test
    fun `quality level second blueprint`() {
        val result = NotEnoughMinerals(listOf(example[1])).sumOfQualityLevels()

        assertThat(result).isEqualTo(24)
    }

    @Test
    fun `max geodes first blueprint`() {
        val result = NotEnoughMinerals(listOf(example[0])).timesOfMaxGeodes()

        assertThat(result).isEqualTo(56)
    }

    @Test
    fun `max geodes second blueprint`() {
        val result = NotEnoughMinerals(listOf(example[1])).timesOfMaxGeodes()

        assertThat(result).isEqualTo(62)
    }
}
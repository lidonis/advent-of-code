package fr.lidonis.adventofcode.y2022.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RegolithReservoirTest {

    private val regolithReservoir = RegolithReservoir("""
        498,4 -> 498,6 -> 496,6
        503,4 -> 502,4 -> 502,9 -> 494,9
    """.trimIndent().lines())

    @Test
    fun `count sand before abyss`() {
        val result = regolithReservoir.countSandBeforeAbyss()
        assertThat(result).isEqualTo(24)
    }

    @Test
    fun `total sand`() {
        val result = regolithReservoir.totalSand()
        assertThat(result).isEqualTo(93)
    }
}

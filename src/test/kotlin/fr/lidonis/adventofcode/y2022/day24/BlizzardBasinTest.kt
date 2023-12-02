package fr.lidonis.adventofcode.y2022.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlizzardBasinTest {
    val lines = """
         #.######
         #>>.<^<#
         #.<..<<#
         #>v.><>#
         #<^v^^>#
         ######.#
    """.trimIndent()

    @Test
    fun `shortest path`() {
        val blizzardBasin = BlizzardBasin(lines)
        val result = blizzardBasin.shortestPath()
        assertThat(result).isEqualTo(18)
    }

    @Test
    fun `shortest path back and forth`() {
        val result = BlizzardBasin(lines).shortestPathBackAndForth()
        assertThat(result).isEqualTo(54)
    }
}

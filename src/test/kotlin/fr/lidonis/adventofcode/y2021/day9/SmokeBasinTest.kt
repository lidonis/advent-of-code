package fr.lidonis.adventofcode.y2021.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SmokeBasinTest {

    val example = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    @Test
    internal fun `sum of the risk levels of all low points`() {
        val smokeBasin = SmokeBasin(example.lines())
        val result = smokeBasin.sumRiskLowestPoints()
        assertThat(result).isEqualTo(15)
    }

    @Test
    internal fun `find 3 largest basins`() {
        val smokeBasin = SmokeBasin(example.lines())
        val result = smokeBasin.findThreeLargestBasinsSize()
        assertThat(result).containsExactlyInAnyOrder(9, 14, 9)
    }
}

package fr.lidonis.adventofcode.y2021.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HydrothermalVentsTest {

    val example = """
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
    """.trimIndent()

    @Test
    internal fun `count at least two lines overlap with horizontal or vertical`() {
        val hydrothermalVents = HydrothermalVents(example.lines())
        val result = hydrothermalVents.countAtLeastTwoLinesOverlapWithHorizontalOrVertical()
        assertThat(result).isEqualTo(5)
    }

    @Test
    internal fun `count at least two lines overlap`() {
        val hydrothermalVents = HydrothermalVents(example.lines())
        val result = hydrothermalVents.countAtLeastTwoLinesOverlap()
        assertThat(result).isEqualTo(12)
    }
}

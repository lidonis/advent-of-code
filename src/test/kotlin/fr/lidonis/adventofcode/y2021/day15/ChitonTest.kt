package fr.lidonis.adventofcode.y2021.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ChitonTest {

    val example = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581

    """.trimIndent()

    @Test
    fun lowestRiskPath() {
        val chiton = Chiton(example.lines())
        val result = chiton.lowestRiskPath()
        assertThat(result).isEqualTo(40)
    }
}
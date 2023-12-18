package fr.lidonis.adventofcode.y2021.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DumboOctopusTest {

    private val simpleExample = """
        11111
        19991
        19191
        19991
        11111
    """.trimIndent()

    @Test
    fun `count flashes simple example 1 step`() {
        val dumboOctopus = DumboOctopus(simpleExample.lines())
        val result = dumboOctopus.countFlashes(1)
        assertThat(result).isEqualTo(9)
    }

    @Test
    fun `count flashes simple example 2 steps`() {
        val dumboOctopus = DumboOctopus(simpleExample.lines())
        val result = dumboOctopus.countFlashes(2)
        assertThat(result).isEqualTo(9)
    }

    private val largeExample = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent()

    @Test
    internal fun `count flashes large example`() {
        val dumboOctopus = DumboOctopus(largeExample.lines())
        val result = dumboOctopus.countFlashes(100)
        assertThat(result).isEqualTo(1656)
    }

    @Test
    internal fun `steps to synchronize`() {
        val dumboOctopus = DumboOctopus(largeExample.lines())
        val result = dumboOctopus.stepToSynchronize()
        assertThat(result).isEqualTo(195)
    }
}

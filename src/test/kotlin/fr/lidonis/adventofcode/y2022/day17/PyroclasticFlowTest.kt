package fr.lidonis.adventofcode.y2022.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PyroclasticFlowTest {
    val input = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"

    @Test
    fun `tower size part 1`() {
        val pyroclasticFlow = PyroclasticFlow(input)
        val result = pyroclasticFlow.towerSize(2022)
        assertThat(result).isEqualTo(3068)
    }

    @Test
    fun `tower size part 2`() {
        val pyroclasticFlow = PyroclasticFlow(input)
        val result = pyroclasticFlow.towerSize(1000000000000)
        assertThat(result).isEqualTo(1514285714288)
    }
}

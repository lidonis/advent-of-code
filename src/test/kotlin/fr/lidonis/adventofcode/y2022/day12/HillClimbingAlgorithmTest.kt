package fr.lidonis.adventofcode.y2022.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HillClimbingAlgorithmTest {
    @Test
    fun `fewest steps from start`() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent()

        val result = HillClimbingAlgorithm(input.lines()).fewestStepsFromStart()

        assertThat(result).isEqualTo(31)
    }

    @Test
    fun `fewest steps from a`() {
        val input = """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
        """.trimIndent()

        val result = HillClimbingAlgorithm(input.lines()).fewestStepsFromA()

        assertThat(result).isEqualTo(29)
    }
}

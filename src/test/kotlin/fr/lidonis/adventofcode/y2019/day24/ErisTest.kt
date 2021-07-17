package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ErisTest {

    @Test
    fun `biodiversity rating`() {
        val eris = Eris(
            """
            .....
            .....
            .....
            #....
            .#...
            """.trimIndent()
        )
        assertThat(eris.biodiversityRating).isEqualTo(2129920)
    }

    @ParameterizedTest(name = "Evolve after {index} minute")
    @MethodSource
    fun evolve(evolution: Pair<Eris, Eris>) {
        assertThat(evolution.first.evolve()).isEqualTo(evolution.second)
    }

    companion object {

        @JvmStatic
        fun evolve() = InputReader("/y2019/day24/part1.txt").text()
            .split("\r\n")
            .map { it.lines().drop(1).joinToString("\n") }
            .map(::Eris)
            .zipWithNext()
    }
}

package fr.lidonis.adventofcode.y2022.day24

import fr.lidonis.adventofcode.common.ResourceReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BlizzardsTest {

    private val state0 = """
        #.#####
        #.....#
        #>....#
        #.....#
        #...v.#
        #.....#
        #####.#
    """.trimIndent()

    private val state1 = """
        #.#####
        #.....#
        #.>...#
        #.....#
        #.....#
        #...v.#
        #####.#
    """.trimIndent()

    private val state2 = """
        #.#####
        #...v.#
        #..>..#
        #.....#
        #.....#
        #.....#
        #####.#
    """.trimIndent()

    private val state3 = """
        #.#####
        #.....#
        #...2.#
        #.....#
        #.....#
        #.....#
        #####.#
    """.trimIndent()

    @Test
    fun display() {
        assertThat(Blizzards.parse(state0).display()).isEqualTo(state0)
    }

    @Test
    fun `evolve 1`() {
        val result = Blizzards.parse(state0).evolve(1)
        assertThat(result.display()).isEqualTo(state1)
    }

    @Test
    fun `evolve 2`() {
        val result = Blizzards.parse(state1).evolve(1)
        assertThat(result.display()).isEqualTo(state2)
    }

    @Test
    fun `evolve 3`() {
        val result = Blizzards.parse(state2).evolve(1)
        assertThat(result.display()).isEqualTo(state3)
    }

    @Test
    fun `evolve steps`() {
        val result = Blizzards.parse(state0).evolve(3)
        assertThat(result.display()).isEqualTo(state3)
    }

    @ParameterizedTest(name = "Evolve after {index} step")
    @MethodSource
    fun complexExample(blizzards: Blizzards, step: Int) {
        val result = complexExample.first().evolve(step)
        assertThat(result.display()).isEqualTo(blizzards.display())
    }

    companion object {

        private val complexExample by lazy {
            (ResourceReader("/y2022/day24/complex-example.txt")?.text() ?: error("Resource not found"))
                .split("${System.lineSeparator()}${System.lineSeparator()}")
                .map { it.lines().drop(1).joinToString(System.lineSeparator()) }
                .map(Blizzards.Companion::parse)
        }

        @JvmStatic
        private fun complexExample() =
            complexExample
                .mapIndexed { step, blizzards -> Arguments.of(blizzards, step) }
    }
}

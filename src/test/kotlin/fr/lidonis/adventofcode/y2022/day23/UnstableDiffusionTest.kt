package fr.lidonis.adventofcode.y2022.day23

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class UnstableDiffusionTest {

    @Nested
    inner class SmallExample {

        private val initialStateSmallExample = """
            .....
            ..##.
            ..#..
            .....
            ..##.
            .....
        """.trimIndent()

        private val state1StateSmallExample = """
            ..##.
            .....
            ..#..
            ...#.
            ..#..
            .....
    """.trimIndent()

        private val state2StateSmallExample = """
            .....
            ..##.
            .#...
            ....#
            .....
            ..#..
    """.trimIndent()

        private val state3StateSmallExample = """
            ..#..
            ....#
            #....
            ....#
            .....
            ..#..
    """.trimIndent()

        @Test
        fun `small example display`() {
            val result = UnstableDiffusion.parse(initialStateSmallExample).display()

            assertThat(result).isEqualTo(initialStateSmallExample)
        }

        @Test
        fun `small example move 1`() {
            val result = UnstableDiffusion.parse(initialStateSmallExample).evolve(1).display()

            assertThat(result).isEqualTo(state1StateSmallExample)
        }

        @Test
        fun `small example move 2`() {
            val result =
                UnstableDiffusion.parse(state1StateSmallExample, OrthogonalDirection.SOUTH).evolve(1).display()

            assertThat(result).isEqualTo(state2StateSmallExample)
        }

        @Test
        fun `small example move 3`() {
            val result =
                UnstableDiffusion.parse(state2StateSmallExample, OrthogonalDirection.WEST).evolve(1).display()

            assertThat(result).isEqualTo(state3StateSmallExample)
        }

        @Test
        fun `small example sequence`() {
            val result = UnstableDiffusion.parse(initialStateSmallExample).evolve(2).display()

            assertThat(result).isEqualTo(state2StateSmallExample)
        }

        private fun UnstableDiffusion.display() = this.display(6, 5)

    }


    @ParameterizedTest(name = "Evolve after {index} step")
    @MethodSource
    fun evolve(current: UnstableDiffusion, evolution: UnstableDiffusion) {
        val result = current.evolve(1).display()
        assertThat(result).isEqualTo(evolution.display())
    }

    @ParameterizedTest(name = "Sequence after {1} step")
    @MethodSource
    fun largerExampleSequence(evolution: UnstableDiffusion, step: Int) {
        val result = largerExample[0].first.evolve(step).display()
        assertThat(result).isEqualTo(evolution.display())
    }

    private fun UnstableDiffusion.display() = this.display(12, 14)

    @Test
    fun count() {
        val result = largerExample[0].first.evolve(10).countEmptyTile()

        assertThat(result).isEqualTo(110)
    }

    @Test
    fun `number of the first round where no Elf moves`() {
        val result = largerExample[0].first.numberOfTheFirstRoundWhereNoElfMoves()

        assertThat(result).isEqualTo(20)
    }

    companion object {

        private val largerExample by lazy {
            InputReader("/y2022/day23/larger-example.txt").text()
                .split("\n\n")
                .map { it.lines().drop(1).joinToString(System.lineSeparator()) }
                .zip(
                    listOf(
                        0 to OrthogonalDirection.NORTH,
                        1 to OrthogonalDirection.SOUTH,
                        2 to OrthogonalDirection.WEST,
                        3 to OrthogonalDirection.EAST,
                        4 to OrthogonalDirection.NORTH,
                        5 to OrthogonalDirection.SOUTH,
                        10 to OrthogonalDirection.SOUTH,
                    )
                )
                .map { (input, data) -> UnstableDiffusion.parse(input, data.second) to data.first }
        }

        @JvmStatic
        fun evolve() = largerExample.dropLast(1)
            .map { it.first }
            .zipWithNext()
            .map { Arguments.of(it.first, it.second) }

        @JvmStatic
        fun largerExampleSequence() = largerExample
            .map { Arguments.of(it.first, it.second) }
    }
}

package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ErisTest {

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

    private val erisesPart2 = readErises("/day24/part2.txt")

    @Test
    fun `evolve example recursively`() {
        val recursiveEris = RecursiveEris(erisesPart2.subList(0, 1))
        assertThat(recursiveEris.evolve(10)).isEqualTo(
            RecursiveEris(erisesPart2.drop(1))
        )
    }

    @Test
    fun `evolve recursively 1`() {
        val recursiveEris = RecursiveEris(
            listOf(
                Eris(
                    input =
                    """
                    ....#
                    #..#.
                    #.?##
                    ..#..
                    #....
                    """.trimIndent()
                )
            )
        )
        assertThat(recursiveEris.evolve(1)).isEqualTo(
            RecursiveEris(
                listOf(
                    Eris(
                        input = """
                        .....
                        ..#..
                        ..?#.
                        ..#..
                        .....
                        """.trimIndent()
                    ),
                    Eris(
                        input = """
                        #..#.
                        ####.
                        ##?.#
                        ##.##
                        .##..
                        """.trimIndent()
                    ),
                    Eris(
                        input = """
                        ....#
                        ....#
                        ..?.#
                        ....#
                        #####
                        """.trimIndent()
                    )

                )
            )
        )
    }

    @Test
    fun `count bugs`() {
        assertThat(RecursiveEris(erisesPart2.drop(1)).count()).isEqualTo(99)
    }

    @Test
    fun `evolve recursive and count bugs`() {
        assertThat(RecursiveEris(erisesPart2.subList(0, 1)).evolve(10).count()).isEqualTo(99)
    }

    companion object {

        @JvmStatic
        fun evolve() = readErises("/day24/part1.txt").zipWithNext()

        private fun readErises(fileName: String) =
            InputReader(fileName).lines()
                .asSequence()
                .filterNot { it.isEmpty() || it[0].isLetter() }
                .chunked(5)
                .map { it.joinToString("\n") }
                .map(::Eris)
                .toList()
    }
}

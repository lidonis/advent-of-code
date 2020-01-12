package fr.lidonis.adventofcode.y2019.day24

import InputReader
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


    @Test
    fun `evolve example recursively`() {
        val erises =
            readErises("day24/part2.txt")
        val recursiveEris = RecursiveEris(erises.subList(0, 1))
        assertThat(recursiveEris.evolve(10)).isEqualTo(
            RecursiveEris(
                erises.drop(1)
            )
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
        val erises =
            readErises("day24/part2.txt")
        assertThat(RecursiveEris(erises.drop(1)).count()).isEqualTo(99)
    }

    @Test
    fun `evolve recursive and count bugs`() {
        val erises = readErises("day24/part2.txt")
        assertThat(RecursiveEris(erises.subList(0, 1)).evolve(10).count()).isEqualTo(99)
    }

    companion object {

        @JvmStatic
        fun evolve() = readErises("day24/part1.txt").zipWithNext()

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
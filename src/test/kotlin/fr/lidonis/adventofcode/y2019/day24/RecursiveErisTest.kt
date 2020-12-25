package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RecursiveErisTest {

    private val erisesPart2 = InputReader("/y2019/day24/part2.txt").text()
        .split("\n\n")
        .map { it.lines().drop(1).joinToString("\n") }
        .map(::Eris)

    @Test
    fun `evolve example recursively`() {
        val recursiveEris = RecursiveEris(erisesPart2.subList(0, 1))
        Assertions.assertThat(recursiveEris.evolve(10)).isEqualTo(
            RecursiveEris(erisesPart2.drop(1))
        )
    }

    @Test
    fun `evolve recursively`() {
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
        Assertions.assertThat(recursiveEris.evolve(1)).isEqualTo(
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
        Assertions.assertThat(RecursiveEris(erisesPart2.drop(1)).countBugs()).isEqualTo(99)
    }

    @Test
    fun `evolve recursive and count bugs`() {
        Assertions.assertThat(RecursiveEris(erisesPart2.subList(0, 1)).evolve(10).countBugs()).isEqualTo(99)
    }

}
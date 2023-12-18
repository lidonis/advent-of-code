package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.ResourceReader
import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class RecursiveErisTest {

    @Test
    fun `count bugs init`() {
        Assertions.assertThat(
            RecursiveEris(
                """
                    ....#
                    #..#.
                    #.?##
                    ..#..
                    #....
                """.trimIndent()
            ).countBugs()
        ).isEqualTo(8)
    }

    @Test
    fun `count bugs recursive`() {
        Assertions.assertThat(RecursiveEris(erisesPart2.drop(1)).countBugs()).isEqualTo(99)
    }

    @Test
    fun `evolve recursively`() {
        val recursiveEris = RecursiveEris(
            """
                    ....#
                    #..#.
                    #.?##
                    ..#..
                    #....
            """.trimIndent()
        )
        Assertions.assertThat(recursiveEris.evolve(1)).isEqualTo(
            RecursiveEris(
                listOf(
                    """
                        Depth -1:
                        .....
                        ..#..
                        ..?#.
                        ..#..
                        .....
                    """.trimIndent(),
                    """
                        Depth 0:
                        #..#.
                        ####.
                        ##?.#
                        ##.##
                        .##..
                    """.trimIndent(),
                    """
                        Depth 1:
                        ....#
                        ....#
                        ..?.#
                        ....#
                        #####
                    """.trimIndent()
                )
            )
        )
    }

    private val erisesPart2 = (ResourceReader("/y2019/day24/part2.txt")?.text() ?: error("Resource not found"))
        .split("\n\n")

    @Test
    fun `evolve example recursively`() {
        val recursiveEris = RecursiveEris(erisesPart2.head.lines().tail.joinToString("\n"))
        Assertions.assertThat(recursiveEris.evolve(10)).isEqualTo(
            RecursiveEris(erisesPart2.drop(1))
        )
    }
}

package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.common.geo.plane.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class AsteroidMapTest {

    @Test
    fun `example 1`() {
        val map = AsteroidMap(
            """
            .#..#
            .....
            #####
            ....#
            ...##
            """.trimIndent()
        )
        assertThat(map.bestStation()).isEqualTo(
            Position(
                3,
                4
            ) to 8
        )
    }

    @Test
    fun `example 2`() {
        val map = AsteroidMap(
            """
                ......#.#.
                #..#.#....
                ..#######.
                .#.#.###..
                .#..#.....
                ..#....#.#
                #..#....#.
                .##.#..###
                ##...#..#.
                .#....####
            """.trimIndent()
        )
        assertThat(map.bestStation()).isEqualTo(
            Position(
                5,
                8
            ) to 33
        )
    }

    @Test
    fun `example 3`() {
        val map = AsteroidMap(
            """
                #.#...#.#.
                .###....#.
                .#....#...
                ##.#.#.#.#
                ....#.#.#.
                .##..###.#
                ..#...##..
                ..##....##
                ......#...
                .####.###.
            """.trimIndent()
        )
        assertThat(map.bestStation()).isEqualTo(
            Position(
                1,
                2
            ) to 35
        )
    }

    @Test
    fun `example 4`() {
        val map = AsteroidMap(
            """
                .#..#..###
                ####.###.#
                ....###.#.
                ..###.##.#
                ##.##.#.#.
                ....###..#
                ..#.#..#.#
                #..#.#.###
                .##...##.#
                .....#.#..
            """.trimIndent()
        )
        assertThat(map.bestStation()).isEqualTo(
            Position(
                6,
                3
            ) to 41
        )
    }

    private val largeExample
        get() = """
            .#..##.###...#######
            ##.############..##.
            .#.######.########.#
            .###.#######.####.#.
            #####.##.#.##.###.##
            ..#####..#.#########
            ####################
            #.####....###.#.#.##
            ##.#################
            #####.##.###..####..
            ..######..##.#######
            ####.##.####...##..#
            .#####..#.######.###
            ##...#.##########...
            #.##########.#######
            .####.#.###.###.#.##
            ....##.##.###..#####
            .#.#.###########.###
            #.#.#.#####.####.###
            ###.##.####.##.#..##
        """.trimIndent()

    @Test
    fun `large example`() {
        val map = AsteroidMap(largeExample)
        assertThat(map.bestStation()).isEqualTo(
            Position(
                11,
                13
            ) to 210
        )
    }

    @ParameterizedTest
    @MethodSource
    fun vaporize(example: Pair<Int, Position>) {
        val map = AsteroidMap(largeExample)
        assertThat(map.vaporize(example.first)).isEqualTo(example.second)
    }

    companion object {

        @JvmStatic
        fun vaporize() = listOf(
            1 to Position(11, 12),
            2 to Position(12, 1),
            3 to Position(12, 2),
            10 to Position(12, 8),
            20 to Position(16, 0),
            50 to Position(16, 9),
            100 to Position(10, 16),
            199 to Position(9, 6),
            200 to Position(8, 2),
            201 to Position(10, 9),
            299 to Position(11, 1)
        )

    }
}
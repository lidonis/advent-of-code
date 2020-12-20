package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TileTest {

    private val tile2311 = Tile(
        """
        Tile 2311:
        ..##.#..#.
        ##..#.....
        #...##..#.
        ####.#...#
        ##.##.###.
        ##...#.###
        .#.#.#..##
        ..#....#..
        ###...#.#.
        ..###..###
        """.trimIndent().lines()
    )

    private val tile3079 = Tile(
        """
        Tile 3079:
        #.#.#####.
        .#..######
        ..#.......
        ######....
        ####.#..#.
        .#...#.##.
        #.#####.##
        ..#.###...
        ..#.......
        ..#.###...
        """.trimIndent().lines()
    )

    private val tile1489 = Tile(
        """
        Tile 1489:
        ##.#.#....
        ..##...#..
        .##..##...
        ..#...#...
        #####...#.
        #..#.#.#.#
        ...#.#.#..
        ##.#...##.
        ..##.##.##
        ###.##.#..
    """.trimIndent().lines()
    )

    private val tile1951 = Tile(
        """
        Tile 1951:
        #.##...##.
        #.####...#
        .....#..##
        #...######
        .##.#....#
        .###.#####
        ###.##.##.
        .###....#.
        ..#.#..#.#
        #...##.#..
        """.trimIndent().lines()
    )

    @Test
    fun fromLines() {
        val input = """
        Tile 2311:
        ..##.#..#.
        #...#.....
        """.trimIndent()
        assertThat(Tile(input.lines())).isEqualTo(
            Tile(
                2311,
                PositionSet(
                    setOf(
                        Position(0, 2), Position(0, 3), Position(0, 5), Position(0, 8),
                        Position(1, 0), Position(1, 4)
                    )
                )
            )
        )
    }

    @Test
    fun match3079() {
        assertThat(tile2311.match(tile3079)).isTrue
    }

    @Test
    fun match1951() {
        assertThat(tile2311.match(tile1951)).isTrue
    }

    @Test
    fun notMatch() {
        assertThat(tile2311.match(tile1489)).isFalse
    }
}
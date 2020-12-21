package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TileTest {

    private val tiles = INPUT.split("\n\n").map { Tile(it.lines()) }.associateBy { it.id }

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

    private fun tile(id: Int) = tiles[id] ?: error("Tile not found")

    @Test
    fun match3079() {
        assertThat(tile(2311).isMatching(tile(3079))).isTrue
    }

    @Test
    fun match1951() {
        assertThat(tile(2311).isMatching(tile(1951))).isTrue
    }

    @Test
    fun notMatch1489() {
        assertThat(tile(2311).isMatching(tile(1489))).isFalse
    }
}

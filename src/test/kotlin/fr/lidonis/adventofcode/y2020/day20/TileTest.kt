package fr.lidonis.adventofcode.y2020.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TileTest {

    private val tiles = INPUT.split("\n\n").map { Tile(it.lines()) }.associateBy { it.id }

    private fun tile(id: Int) = tiles[id] ?: error("Tile not found")

    @Test
    fun top() {
        assertThat(tile(2311).top).isEqualTo(listOf(false, false, true, true, false, true, false, false, true, false))
    }

    @Test
    fun right() {
        assertThat(tile(2311).right).isEqualTo(listOf(false, false, false, true, false, true, true, false, false, true))
    }

    @Test
    fun bottom() {
        assertThat(tile(2311).bottom).isEqualTo(listOf(false, false, true, true, true, false, false, true, true, true))
    }

    @Test
    fun left() {
        assertThat(tile(2311).left).isEqualTo(listOf(false, true, true, true, true, true, false, false, true, false))
    }

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

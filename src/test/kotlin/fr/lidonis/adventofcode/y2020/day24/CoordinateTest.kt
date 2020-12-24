package fr.lidonis.adventofcode.y2020.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CoordinateTest {

    @Test
    fun init() {
        assertThat(Coordinate.fromDirections("")).isEqualTo(Coordinate(0, 0))
    }

    @Test
    fun e() {
        assertThat(Coordinate.fromDirections("e")).isEqualTo(Coordinate(1, 0))
    }

    @Test
    fun se() {
        assertThat(Coordinate.fromDirections("se")).isEqualTo(Coordinate(0, 1))
    }

    @Test
    fun esew() {
        assertThat(Coordinate.fromDirections("esew")).isEqualTo(Coordinate(0, 1))
    }

    @Test
    fun nwwswee() {
        assertThat(Coordinate.fromDirections("nwwswee")).isEqualTo(Coordinate(0, 0))
    }
}

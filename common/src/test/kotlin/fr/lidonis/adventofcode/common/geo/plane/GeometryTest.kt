package fr.lidonis.adventofcode.common.geo.plane

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GeometryTest {

    private val polygon = listOf(
        Position(1, 6),
        Position(3, 1),
        Position(7, 2),
        Position(4, 4),
        Position(8, 5),
    ).map { it.toLong() }

    @Test
    fun shoelaceFormula() {
        val result = polygon.shoelaceFormula()
        assertThat(result).isEqualTo(16.5)
    }

    @Test
    fun perimeter() {
        val result = polygon.perimeter()
        assertThat(result).isEqualTo(15)
    }
}

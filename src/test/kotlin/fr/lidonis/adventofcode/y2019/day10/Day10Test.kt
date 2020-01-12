package fr.lidonis.adventofcode.y2019.day10

import InputReader
import fr.lidonis.adventofcode.common.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `part 1`() {
        val map = AsteroidMap(InputReader("day10.txt").text())
        assertThat(map.bestStation()?.second).isEqualTo(274)
    }

    @Test
    fun `part 2`() {
        val map = AsteroidMap(InputReader("day10.txt").text())
        assertThat(map.vaporize(200)).isEqualTo(Position(3, 5))
    }
}
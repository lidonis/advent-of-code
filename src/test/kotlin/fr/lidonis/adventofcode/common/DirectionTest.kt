package fr.lidonis.adventofcode.common

import fr.lidonis.adventofcode.common.geo.plane.Direction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DirectionTest {

    @Test
    fun `test north turn right is east`() {
        assertThat(Direction.UP.turnRight()).isEqualTo(
            Direction.RIGHT
        )
    }

    @Test
    fun `test east turn right is south`() {
        assertThat(Direction.RIGHT.turnRight()).isEqualTo(
            Direction.DOWN
        )
    }

    @Test
    fun `test south turn right is west`() {
        assertThat(Direction.DOWN.turnRight()).isEqualTo(
            Direction.LEFT
        )
    }

    @Test
    fun `test west turn right is north`() {
        assertThat(Direction.LEFT.turnRight()).isEqualTo(
            Direction.UP
        )
    }

    @Test
    fun `test north turn left is west`() {
        assertThat(Direction.UP.turnLeft()).isEqualTo(
            Direction.LEFT
        )
    }

    @Test
    fun `test west turn left is south`() {
        assertThat(Direction.LEFT.turnLeft()).isEqualTo(
            Direction.DOWN
        )
    }

    @Test
    fun `test south turn left is east`() {
        assertThat(Direction.DOWN.turnLeft()).isEqualTo(
            Direction.RIGHT
        )
    }

    @Test
    fun `test east turn left is north`() {
        assertThat(Direction.RIGHT.turnLeft()).isEqualTo(
            Direction.UP
        )
    }
}

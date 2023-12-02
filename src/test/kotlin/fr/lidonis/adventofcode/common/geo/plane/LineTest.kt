package fr.lidonis.adventofcode.common.geo.plane

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LineTest {

    private val horizontalLine = Line(Position(1, 3), Position(1, 5))
    private val verticalLine = Line(Position(4, 2), Position(8, 2))
    private val diagonalLine = Line(Position(1, 1), Position(4, 4))

    @Test
    internal fun `horizontal line is horizontal`() {
        assertThat(horizontalLine.isHorizontal).isTrue
    }

    @Test
    internal fun `vertical line is not horizontal`() {
        assertThat(verticalLine.isHorizontal).isFalse
    }

    @Test
    internal fun `diagonal is not horizontal`() {
        assertThat(diagonalLine.isHorizontal).isFalse
    }

    @Test
    internal fun `horizontal line is not vertical`() {
        assertThat(horizontalLine.isVertical).isFalse
    }

    @Test
    internal fun `vertical line is vertical`() {
        assertThat(verticalLine.isVertical).isTrue
    }

    @Test
    internal fun `diagonal is not vertical`() {
        assertThat(diagonalLine.isVertical).isFalse
    }

    @Test
    internal fun `all positions x1=x2`() {
        val result = horizontalLine.allPositions
        assertThat(result).isEqualTo(
            PositionSet(
                setOf(
                    Position(1, 3),
                    Position(1, 4),
                    Position(1, 5)
                )
            )
        )
    }

    @Test
    internal fun `all positions y1=y2`() {
        val result = verticalLine.allPositions
        assertThat(result).isEqualTo(
            PositionSet(
                setOf(
                    Position(4, 2),
                    Position(5, 2),
                    Position(6, 2),
                    Position(7, 2),
                    Position(8, 2),
                )
            )
        )
    }

    @Test
    internal fun `all positions diagonal`() {
        val result = diagonalLine.allPositions
        assertThat(result).isEqualTo(
            PositionSet(
                setOf(
                    Position(1, 1),
                    Position(2, 2),
                    Position(3, 3),
                    Position(4, 4),
                )
            )
        )
    }
}

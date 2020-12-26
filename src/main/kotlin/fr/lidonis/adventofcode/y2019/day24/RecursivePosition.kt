package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.geo.plane.Position

private val CENTER = Position(SIZE / 2, SIZE / 2)
private val UP = Position(0, -1)
private val LEFT = Position(-1, 0)
private val RIGHT = Position(1, 0)
private val DOWN = Position(0, 1)
private val LEVEL_UP = RecursivePosition(0, 0, -1)
private val LEVEL_DOWN = RecursivePosition(0, 0, 1)

data class RecursivePosition(val x: Int, val y: Int, val z: Int) {

    operator fun plus(move: RecursivePosition) = RecursivePosition(x + move.x, y + move.y, z + move.z)

    operator fun plus(move: Position) = RecursivePosition(x + move.x, y + move.y, z)

    fun neighbours() = up() + left() + right() + down()

    private fun up() = when {
        y == 0 -> setOf(centerLevelUp() + UP)
        Position(x, y) == CENTER + DOWN -> levelDownNeighbours { Position(it, SIZE - 1) }
        else -> setOf(this + UP)
    }

    private fun left() = when {
        x == 0 -> setOf(centerLevelUp() + LEFT)
        Position(x, y) == CENTER + RIGHT -> levelDownNeighbours { Position(SIZE - 1, it) }
        else -> setOf(this + LEFT)
    }

    private fun right() = when {
        x == SIZE - 1 -> setOf(centerLevelUp() + RIGHT)
        Position(x, y) == CENTER + LEFT -> levelDownNeighbours { Position(0, it) }
        else -> setOf(this + RIGHT)
    }

    private fun down() = when {
        y == SIZE - 1 -> setOf(centerLevelUp() + DOWN)
        Position(x, y) == CENTER + UP -> levelDownNeighbours { Position(it, 0) }
        else -> setOf(this + DOWN)
    }

    private fun centerLevelUp() = RecursivePosition(0, 0, z) + LEVEL_UP + CENTER

    private fun levelDownNeighbours(position: (Int) -> Position) =
        (0 until SIZE).map { RecursivePosition(0, 0, z) + LEVEL_DOWN + position(it) }.toSet()
}

package fr.lidonis.adventofcode.common.geo.plane

import fr.lidonis.adventofcode.common.math.pow

data class PositionSet(val positions: Set<Position>) : Set<Position> by positions {

    fun mirrorY() = PositionSet(map { Position(it.x, -it.y) }.toSet())

    fun chunked(size: Int) = groupBy { it.x / size }.values.map { PositionSet(it.toSet()) }

    fun moveTo(position: Position) = PositionSet(map { it - boundingBox.start + position }.toSet())

    private val boundingBox by lazy {
        if (isEmpty()) {
            BoundingBox(Position.ORIGIN, Position.ORIGIN)
        } else {
            val min = positions.reduce { acc, position ->
                Position(acc.x.coerceAtMost(position.x), acc.y.coerceAtMost(position.y))
            }
            val max = positions.reduce { acc, position ->
                Position(acc.x.coerceAtLeast(position.x), acc.y.coerceAtLeast(position.y))
            }
            BoundingBox(min, max)
        }
    }

    val score = map { 2.pow(it.x * boundingBox.end.x + it.y) }.sum()

    data class BoundingBox(val start: Position, var end: Position)
}

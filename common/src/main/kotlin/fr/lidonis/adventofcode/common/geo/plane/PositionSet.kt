package fr.lidonis.adventofcode.common.geo.plane

import fr.lidonis.adventofcode.common.math.pow

data class PositionSet(val positions: Set<Position>) : Set<Position> by positions {

    fun mirrorY() = PositionSet(map { Position(it.x, -it.y) }.toSet())

    fun chunked(size: Int) = groupBy { it.x / size }.toSortedMap().values.map { PositionSet(it.toSet()) }

    fun moveTo(position: Position) = PositionSet(map { it - boundingBox.start + position }.toSet())

    val boundingBox by lazy {
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

    val score = sumOf { 2.pow(it.x * boundingBox.end.x + it.y) }

    @Suppress("unused")
    fun display(displays: Set<Position> = emptySet()) {
        println(buildString(displays))
    }

    fun buildString(displays : Set<Position>) = buildString {
        for (j in (boundingBox.start.y)..boundingBox.end.y) {
            for (i in (boundingBox.start.x)..boundingBox.end.x) {
                when {
                    Position(i, j) in positions -> append("#")
                    Position(i, j) in displays -> append("X")
                    else -> append(".")
                }
            }
            appendLine()
        }
    }

    data class BoundingBox(val start: Position, var end: Position) {
        operator fun contains(position: Position) =
            position.x in start.x..end.x && position.y in start.y..end.y
    }
}

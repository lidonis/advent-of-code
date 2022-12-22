package fr.lidonis.adventofcode.common.geo.plane

import fr.lidonis.adventofcode.common.math.pow

data class PositionSet(val positions: Set<Position>) : Set<Position> by positions {

    fun mirrorY() = PositionSet(map { Position(it.x, -it.y) }.toSet())

    fun chunkedX(size: Int) = groupBy { it.x / size }.toSortedMap().values.map { PositionSet(it.toSet()) }

    fun moveTo(position: Position) = PositionSet(map { it - boundingBox.start + position }.toSet())

    fun moveBy(position: Position) = PositionSet(map { it + position }.toSet())

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

    fun display(boundingBox1: BoundingBox = boundingBox) {
        println(buildString(boundingBox1))
    }

    fun buildString(boundingBox1: BoundingBox = boundingBox) = buildString {
        for (j in (boundingBox1.end.y) downTo boundingBox1.start.y) {
            for (i in (boundingBox1.start.x)..boundingBox1.end.x) {
                if (Position(i, j) in positions) append("#") else append(".")
            }
            appendLine()
        }
    }

    data class BoundingBox(val start: Position, var end: Position)
}

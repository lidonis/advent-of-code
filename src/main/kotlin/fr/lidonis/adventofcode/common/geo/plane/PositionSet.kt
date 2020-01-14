package fr.lidonis.adventofcode.common.geo.plane

data class PositionSet(val positions: Set<Position>) : Set<Position> by positions {

    fun mirrorY() = PositionSet(map { Position(it.x, -it.y) }.toSet())

    fun chunked(size: Int) = groupBy { it.x / size }.values.map { PositionSet(it.toSet()) }

    fun moveTo(position: Position) = PositionSet(map { it - boundingBox.start + position }.toSet())

    val boundingBox by lazy {
        if (isEmpty()) {
            BoundingBox(Position.ORIGIN, Position.ORIGIN)
        } else {
            var (xMin, yMin) = first()
            var (xMax, yMax) = first()
            for (position in drop(1)) {
                if (position.x < xMin) xMin = position.x
                if (position.y < yMin) yMin = position.y
                if (position.x > xMax) xMax = position.x
                if (position.y > yMax) yMax = position.y
            }
            BoundingBox(Position(xMin, yMin), Position(xMax, yMax))
        }
    }

    data class BoundingBox(val start: Position, var end: Position)
}

import kotlin.math.absoluteValue

data class Position(val x: Int, val y: Int) {

    fun move(direction: Direction) =
        when (direction) {
            Direction.R -> Position(x + 1, y)
            Direction.D -> Position(x, y - 1)
            Direction.L -> Position(x - 1, y)
            Direction.U -> Position(x, y + 1)
        }

    fun move(cardinalPoint: CardinalPoint) =
        when (cardinalPoint) {
            CardinalPoint.NORTH -> Position(x, y - 1)
            CardinalPoint.EAST -> Position(x + 1, y)
            CardinalPoint.SOUTH -> Position(x, y + 1)
            CardinalPoint.WEST -> Position(x - 1, y)
        }

    fun neighbours() = CardinalPoint.values().map { this.move(it) }

    fun manhattanDistance(other: Position) = (x - other.x).absoluteValue + (y - other.y).absoluteValue
}

enum class Direction {
    U, R, D, L;

    operator fun inc() = values()[Math.floorMod(this.ordinal + 1, values().size)]
    operator fun dec() = values()[Math.floorMod(this.ordinal - 1, values().size - 1)]
}

enum class CardinalPoint {
    NORTH, EAST, SOUTH, WEST;

    operator fun inc() = turnRight()
    operator fun dec() = turnLeft()

    fun turnRight() = values()[Math.floorMod(this.ordinal + 1, values().size)]
    fun turnLeft() = values()[Math.floorMod(this.ordinal - 1, values().size)]
}
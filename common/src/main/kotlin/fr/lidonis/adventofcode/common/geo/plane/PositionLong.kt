package fr.lidonis.adventofcode.common.geo.plane

data class PositionLong(val x: Long, val y: Long) {

    operator fun plus(move: Position) = add(this, move.toLong())
    operator fun plus(direction: DirectionUpNegative) = this + direction.move

    companion object {
        val ORIGIN = PositionLong(0, 0)

        fun add(a: PositionLong, b: PositionLong) =
            PositionLong(a.x + b.x, a.y + b.y)
    }
}

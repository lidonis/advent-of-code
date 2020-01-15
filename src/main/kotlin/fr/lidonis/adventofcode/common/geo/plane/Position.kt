package fr.lidonis.adventofcode.common.geo.plane

import fr.lidonis.adventofcode.common.atan2
import fr.lidonis.adventofcode.common.pow
import kotlin.math.abs
import kotlin.math.sqrt

data class Position(val x: Int, val y: Int) {

    operator fun plus(move: Position) =
        add(this, move)

    operator fun minus(move: Position) = this + Position(
        -move.x,
        -move.y
    )

    operator fun plus(direction: Direction) =
        add(this, direction.move)

    fun neighbours() = Direction.values().map { this + it }

    fun manhattanDistance(other: Position) = abs(x - other.x) + abs(y - other.y)

    fun angle(target: Position) = atan2(target.x - x, target.y - y)

    fun distance(target: Position) = distance(this, target)

    companion object {

        val ORIGIN = Position(0, 0)

        fun add(a: Position, b: Position) =
            Position(a.x + b.x, a.y + b.y)

        fun distance(a: Position, b: Position) = sqrt(((b.y - a.y).pow(2) + (b.x - a.x).pow(2)).toDouble())
    }
}

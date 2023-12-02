package fr.lidonis.adventofcode.common.geo.plane

import fr.lidonis.adventofcode.common.math.atan2
import fr.lidonis.adventofcode.common.math.pow
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin
import kotlin.math.sqrt

data class Position(val x: Int, val y: Int) {

    constructor(x: Double, y: Double) : this(x.roundToInt(), y.roundToInt())

    operator fun plus(move: Position) = add(this, move)

    operator fun minus(move: Position) = this + Position(-move.x, -move.y)

    operator fun times(value: Int) = Position(x * value, y * value)

    operator fun plus(direction: Direction) = this + direction.move

    fun neighbours() = Direction.entries.map { this + it }

    fun manhattanDistance(other: Position) = abs(x - other.x) + abs(y - other.y)

    fun angle(target: Position) =
        atan2(target.x - x, target.y - y)

    fun distance(target: Position) = distance(this, target)

    fun rotate(radian: Double) = Position(
        x * cos(radian) + y * sin(radian),
        y * cos(radian) - x * sin(radian)
    )

    companion object {

        val ORIGIN = Position(0, 0)

        fun add(a: Position, b: Position) =
            Position(a.x + b.x, a.y + b.y)

        fun distance(a: Position, b: Position) = sqrt(((b.y - a.y).pow(2) + (b.x - a.x).pow(2)).toDouble())
    }
}

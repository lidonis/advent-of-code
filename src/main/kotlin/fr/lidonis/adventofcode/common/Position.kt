package fr.lidonis.adventofcode.common

import kotlin.math.absoluteValue
import kotlin.math.atan2
import kotlin.math.sqrt

data class Position(val x: Int, val y: Int) {

    fun move(direction: Direction) =
        when (direction) {
            Direction.RIGHT -> Position(x + 1, y)
            Direction.DOWN -> Position(x, y - 1)
            Direction.LEFT -> Position(x - 1, y)
            Direction.UP -> Position(x, y + 1)
        }

    fun neighbours() = Direction.values().map { this.move(it) }

    fun manhattanDistance(other: Position) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

    fun angle(target: Position) =
        atan2((target.x - x).toDouble(), (target.y - y).toDouble())

    fun distance(target: Position) =
        distance(this, target)

    companion object {
        fun distance(a: Position, b: Position) =
            sqrt(((b.y - a.y) pow 2 + (b.x - a.x) pow 2).toDouble())
    }
}


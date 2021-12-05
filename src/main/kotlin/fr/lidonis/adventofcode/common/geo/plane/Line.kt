package fr.lidonis.adventofcode.common.geo.plane

import kotlin.math.abs
import kotlin.math.max

data class Line(val a: Position, val b: Position) {
    val isHorizontal = a.x == b.x
    val isVertical = a.y == b.y

    val allPositions = (0..max(abs(a.x - b.x), abs(a.y - b.y))).map { step ->
        val x = when {
            b.x > a.x -> a.x + step
            b.x < a.x -> a.x - step
            else -> a.x
        }
        val y = when {
            b.y > a.y -> a.y + step
            b.y < a.y -> a.y - step
            else -> a.y
        }
        Position(x, y)
    }.toSet()

}

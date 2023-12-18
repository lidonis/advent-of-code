package fr.lidonis.adventofcode.common.geo.plane

import kotlin.math.absoluteValue

fun Iterable<PositionLong>.shoelaceFormula() =
    (
        zipWithNext().sumOf { (p1, p2) ->
            determinant(p1, p2)
        } + determinant(last(), first())
        ).absoluteValue.toDouble() / 2

private fun determinant(p1: PositionLong, p2: PositionLong) =
    p1.x * p2.y - p1.y * p2.x

fun Iterable<PositionLong>.perimeter() =
    (
        zipWithNext().sumOf { (p1, p2) ->
            edgeSize(p1, p2)
        } + edgeSize(last(), first())
        ).absoluteValue / 2

private fun edgeSize(p1: PositionLong, p2: PositionLong) =
    (p2.x - p1.x).absoluteValue + (p2.y - p1.y).absoluteValue

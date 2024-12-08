package fr.lidonis.adventofcode.y2024.day8

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class ResonantCollinearity(private val lines: List<String>) {

    private val boundingBox = PositionSet.BoundingBox(Position.ORIGIN, Position(lines[0].lastIndex, lines.lastIndex))
    private val antennaPairs = createAntennaPairs()

    fun part1() = antennaPairs.flatMap { (a1, a2) ->
        listOf(
            Position(a1.x * 2 - a2.x, a1.y * 2 - a2.y),
            Position(a2.x * 2 - a1.x, a2.y * 2 - a1.y)
        )
    }.toSet().filter { boundingBox.contains(it) }.size

    fun part2() = boundingBox.positions().count { position ->
        antennaPairs.any { (a1, a2) -> areAligned(a1, a2, position) }
    }

    private fun areAligned(p1: Position, p2: Position, p3: Position) =
        (p2.y - p1.y) * (p3.x - p2.x) == (p3.y - p2.y) * (p2.x - p1.x)

    private fun createAntennaPairs() =
        buildPositionMap()
            .groupBy { it.second }
            .flatMap { (_, antennas) ->
                antennas.flatMapIndexed { index, (a1) ->
                    antennas.drop(index + 1).map { (a2) -> a1 to a2 }
                }
            }

    private fun buildPositionMap() = sequence {
        for ((y, line) in lines.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char.isLetterOrDigit()) yield(Position(x, y) to char)
            }
        }
    }
}

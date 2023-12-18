package fr.lidonis.adventofcode.y2023.day18

import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class LavaductLagoon(private val lines: List<String>) {

    fun part1(): Int {
        val positionSet = PositionSet(buildSet {
            var current = Position.ORIGIN
            add(current)
            for (line in lines) {
                val (d, l) = line.split(" ")
                val direction = DirectionUpNegative.fromLetter(d.first()) ?: error("Invalid direction")
                val distance = l.toInt()
                for (i in 1..distance) {
                    current += direction
                    add(current)
                }
            }
        })
        positionSet.display()
        val countEnclosed = countEnclosed(positionSet.positions)
        return countEnclosed + positionSet.size
    }

    private fun countEnclosed(loopPositions: Set<Position>): Int {
        var enclosed = 0
        val boundingBox = PositionSet(loopPositions).boundingBox
        for (j in (boundingBox.start.y)..boundingBox.end.y) {
            for (i in (boundingBox.start.x)..boundingBox.end.x) {
                enclosed += countEnclosedRegions(Position(i, j), loopPositions, boundingBox.end.x)
            }
        }
        return enclosed
    }

    private fun countEnclosedRegions(
        position: Position,
        loopPositions: Set<Position>,
        mapSize: Int
    ): Int {
        if (position !in loopPositions) {
            var found = 0
            for (k in position.x..mapSize) {
                if (Position(k, position.y) in loopPositions) {
                    found++
                }
            }
            if (found % 2 == 1) {
                return 1
            }
        }
        return 0
    }

    fun part2() = lines.size
}

package fr.lidonis.adventofcode.y2023.day21

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class StepCounter(lines: List<String>) {

    private val map = lines.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            when (c) {
                '.' -> Position(x, y) to true
                'S' -> Position(x, y) to false
                else -> null
            }
        }
    }.partition { it.second }
    private val starts = map.second.map { it.first }.toSet()
    private val gardenPlots = map.first.map { it.first }.toSet() + starts
    private val boundingBox = PositionSet(gardenPlots).boundingBox

    fun part1(steps: Int) = next().elementAt(steps).size

    private fun buildString(boundingBox: PositionSet.BoundingBox, gardenPlots: Set<Position>, starts: Set<Position>) =
        buildString {
            for (j in (boundingBox.start.y)..boundingBox.end.y) {
                for (i in (boundingBox.start.x)..boundingBox.end.x) {
                    when {
                        Position(i, j) in starts-> append("O")
                        gardenPlots.containsMod(Position(i, j), boundingBox)-> append(".")
                        else -> append("#")
                    }
                }
                appendLine()
            }
        }

    fun next() = generateSequence(starts) { positions ->
        positions.flatMap { position ->
            position
                .neighbours()
                .filter {
                    gardenPlots.containsMod(position, boundingBox)
                }
        }.toSet()
    }

    fun part2() {
        val elves = next().elementAt(1)
        val boundingBox1 = PositionSet(elves).boundingBox
        println(boundingBox1)
        println(buildString(boundingBox1, gardenPlots, elves))
    }

}

fun Set<Position>.containsMod(position: Position, boundingBox: PositionSet.BoundingBox) =
    Position(position.x % boundingBox.end.x, position.y % boundingBox.end.y) in this

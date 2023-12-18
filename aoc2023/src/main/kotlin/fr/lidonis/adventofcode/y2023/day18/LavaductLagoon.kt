package fr.lidonis.adventofcode.y2023.day18

import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative
import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative.*
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class LavaductLagoon(private val lines: List<String>) {
    private val upPosition = mutableSetOf<Position>()
    private val downPosition = mutableSetOf<Position>()
    private val positions = buildMap {
        var current = Position.ORIGIN
        for (line in lines) {
            val (d, l) = line.split(" ")
            val direction = DirectionUpNegative.fromLetter(d.first()) ?: error("Invalid direction")
            val distance = l.toInt()
            for (i in 1..distance) {
                current += direction
                put(current, direction)
                if (direction == UP) upPosition.add(current)
                if (direction == DOWN) downPosition.add(current)
            }
        }
    }
    val boundingBox = PositionSet(positions.keys).boundingBox
    val enclosed = mutableSetOf<Position>()

    fun part1(): Int {
        val countEnclosed = countEnclosed()
        println(buildString())
        return countEnclosed + positions.size
    }

    private fun buildString() = buildString {
        for (j in (boundingBox.start.y)..boundingBox.end.y) {
            for (i in (boundingBox.start.x)..boundingBox.end.x) {
                when (val position = Position(i, j)) {
                    in positions -> append(positions[position]?.letter)
                    in enclosed -> append('O')
                    else -> append('.')
                }
            }
            appendLine()
        }
    }

    private fun countEnclosed(): Int {
        for (j in (boundingBox.start.y)..boundingBox.end.y) {
            for (i in (boundingBox.start.x)..boundingBox.end.x) {
                val position = Position(i, j)
                if(isEnclosedRegions(position)){
                  enclosed.add(position)
                 }
            }
        }
        return enclosed.size
    }

    private fun isEnclosedRegions(
        position: Position,
    ): Boolean {
        if (position !in positions) {
            var left: DirectionUpNegative? = null
            for (i in position.x downTo boundingBox.start.x){
                when(positions[Position(i, position.y)]){
                    UP -> { left = UP; break}
                    RIGHT -> { left = RIGHT; break}
                    null -> continue
                    else ->  { break }
                }
            }
            var right: DirectionUpNegative? = null
            for (i in position.x .. boundingBox.end.x){
                when(positions[Position(i, position.y)]){
                    DOWN -> { right = DOWN; break}
                    LEFT -> { right = LEFT; break}
                    null -> continue
                    else ->  { }
                }
            }
            return left != null && right != null
        }
        return false
    }

    fun part2() = lines.size
}

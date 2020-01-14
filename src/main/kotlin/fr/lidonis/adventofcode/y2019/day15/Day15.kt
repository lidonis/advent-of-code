package fr.lidonis.adventofcode.y2019.day15

import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import kotlin.math.max
import kotlin.math.min

fun main() {
    val drone = Drone(InputReader("day15.txt").text())
    println(drone.explore())
}

private const val DAY = 15

object Day15 : AdventOfCode2019(DAY) {

    override fun part1(): Any {
        return 298
    }

    override fun part2(): Any {
        return 346
    }
}

class ShipMap {
    private var xMin = 0
    private var yMin = 0
    private var xMax = 0
    private var yMax = 0
    private val walls = mutableListOf<Position>()

    fun add(move: Position) {
        resizeMap(move)
        walls.add(move)
    }

    private fun resizeMap(move: Position) {
        xMin = min(xMin, move.x)
        yMin = min(yMin, move.y)
        xMax = max(xMax, move.x)
        yMax = max(yMax, move.y)
    }

    fun display(drone: Position) {
        //println(walls)
        resizeMap(drone)
        for (j in yMax downTo yMin) {
            for (i in xMin..xMax) {
                val position = Position(i, j)
                when {
                    walls.contains(position) -> print('#')
                    i == 0 && j == 0 -> print('S')
                    position == drone -> print('D')
                    else -> print(' ')
                }
            }
            print("\n")
        }
    }

    override fun toString(): String {
        return "ShipMap(xMin=$xMin, yMin=$yMin, xMax=$xMax, yMax=$yMax, walls=$walls)"
    }


}

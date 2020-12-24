package fr.lidonis.adventofcode.y2019.day15

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory
import kotlin.math.max
import kotlin.math.min

class Drone(program: String) {
    private val computer =
        IntCodeComputerFactory.buildIOComputer(program)
    private var direction = Direction.UP
    private var position = Position.ORIGIN
    private val shipMap = ShipMap()

    fun explore(): Position {
        loop@ while (true) {
            computer.input(direction.ordinal + 1L)
            when (computer.nextOutput()) {
                0L -> {
                    shipMap.add(position + direction)
                }
                1L -> {
                    position += direction
                }
                2L -> break@loop
            }
            direction++

        }
        shipMap.display(position)
        return position
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
        resizeMap(drone)
        for (j in yMax downTo yMin) {
            for (i in xMin..xMax) {
                val position = Position(i, j)
                when {
                    position in walls -> print('#')
                    i == 0 && j == 0 -> print('S')
                    position == drone -> print('D')
                    else -> print(' ')
                }
            }
            println()
        }
    }

    override fun toString(): String {
        return "ShipMap(xMin=$xMin, yMin=$yMin, xMax=$xMax, yMax=$yMax, walls=$walls)"
    }
}

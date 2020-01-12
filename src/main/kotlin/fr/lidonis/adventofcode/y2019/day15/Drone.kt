package fr.lidonis.adventofcode.y2019.day15

import fr.lidonis.adventofcode.common.Direction
import fr.lidonis.adventofcode.common.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class Drone(program: String) {
    private val computer =
        IntCodeComputerFactory.buildIOComputer(program)
    private var direction = Direction.UP
    private var position = Position(0, 0)
    private val shipMap = ShipMap()

    fun explore(): Position {
        loop@ while (true) {
            computer.input(direction.ordinal + 1L)
            //println("input $direction $status $position")
            when (computer.nextOutput()) {
                0L -> {
                    shipMap.add(position.move(direction))
                    //println("wall")
                }
                1L -> {
                    position = position.move(direction)
                    //println("move")
                }
                2L -> break@loop
            }
            direction++

        }
        shipMap.display(position)
        return position
    }
}
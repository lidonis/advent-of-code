package fr.lidonis.adventofcode.y2020.day12

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.Direction.RIGHT as EAST

private const val WAYPOINT_ORIGIN_X = 10
private const val WAYPOINT_ORIGIN_Y = 1

class NavigationSystem(lines: List<String>) {
    private val instructions =
        lines.map { Instruction(Action.valueOf(it[0]) ?: error("Unknown state"), it.drop(1).toInt()) }

    fun distanceDirection() = distance(DirectionShip(Position.ORIGIN, EAST))

    private fun distance(ship: Ship): Int {
        instructions.forEach { move(it, ship) }
        return Position.ORIGIN.manhattanDistance(ship.position)
    }

    fun distanceWaypoint() = distance(WaypointShip(Position.ORIGIN, Position(WAYPOINT_ORIGIN_X, WAYPOINT_ORIGIN_Y)))

    private fun move(instruction: Instruction, ship: Ship) =
        when (instruction.action) {
            Action.NORTH -> ship.move(Position(0, instruction.value))
            Action.SOUTH -> ship.move(Position(0, -instruction.value))
            Action.EAST -> ship.move(Position(instruction.value, 0))
            Action.WEST -> ship.move(Position(-instruction.value, 0))
            Action.RIGHT -> ship.rotate(instruction.value)
            Action.LEFT -> ship.rotate(-instruction.value)
            Action.FORWARD -> ship.forward(instruction.value)
        }

    data class Instruction(val action: Action, val value: Int)

    enum class Action(val c: Char) {
        NORTH('N'), SOUTH('S'), EAST('E'), WEST('W'), LEFT('L'), RIGHT('R'), FORWARD('F');

        companion object {
            fun valueOf(c: Char) = values().find { it.c == c }
        }
    }

}

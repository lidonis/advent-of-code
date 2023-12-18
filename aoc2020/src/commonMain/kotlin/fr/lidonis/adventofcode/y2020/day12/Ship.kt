package fr.lidonis.adventofcode.y2020.day12

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position

interface Ship {
    var position: Position
    fun move(move: Position)
    fun rotate(degrees: Int)
    fun forward(value: Int)
}

object Angle {
    const val RIGHT_ANGLE_DEGREES = 90
}

data class DirectionShip(override var position: Position, var direction: Direction) : Ship {

    override fun move(move: Position) {
        position += move
    }

    override fun rotate(degrees: Int) {
        direction += degrees / Angle.RIGHT_ANGLE_DEGREES
    }

    override fun forward(value: Int) {
        this.position += this.direction.move * value
    }
}

data class WaypointShip(override var position: Position, var waypoint: Position) : Ship {

    override fun move(move: Position) {
        waypoint += move
    }

    override fun rotate(degrees: Int) {
        waypoint = waypoint.rotate(Math.toRadians(degrees.toDouble()))
    }

    override fun forward(value: Int) {
        position += waypoint * value
    }
}

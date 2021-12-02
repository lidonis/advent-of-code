package fr.lidonis.adventofcode.y2021.day2

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2021.day2.Direction.DOWN
import fr.lidonis.adventofcode.y2021.day2.Direction.FORWARD
import fr.lidonis.adventofcode.y2021.day2.Direction.UP

private enum class Direction {
    FORWARD, UP, DOWN
}

private data class Command(val direction: Direction, val value: Int)

class SubmarineCourse(course: List<String>) {
    private val mappedCourse = course.map { instruction ->
        val split = instruction.split(" ")
        when (split[0]) {
            "forward" -> Command(FORWARD, split[1].toInt())
            "up" -> Command(UP, split[1].toInt())
            "down" -> Command(DOWN, split[1].toInt())
            else -> error("command unknown")
        }
    }

    fun plannedCourse() = mappedCourse.map {
        when (it.direction) {
            FORWARD -> Position(it.value, 0)
            UP -> Position(0, -it.value)
            DOWN -> Position(0, it.value)
        }
    }.fold(Position.ORIGIN) { a, b -> a + b }

    fun plannedCourseWithAim() = mappedCourse.fold(Position.ORIGIN to 0) { acc, current ->
        when (current.direction) {
            FORWARD -> (acc.first + Position(current.value, current.value * acc.second)) to acc.second
            UP -> acc.first to (acc.second - current.value)
            DOWN -> acc.first to (acc.second + current.value)
        }
    }.first
}

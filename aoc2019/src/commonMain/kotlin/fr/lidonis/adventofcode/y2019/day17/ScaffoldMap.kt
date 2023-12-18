package fr.lidonis.adventofcode.y2019.day17

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position

class VacuumRobot(private var position: Position, private var direction: Direction) {
    val forward get() = position + direction

    fun moveForward() {
        position = forward
    }

    val left get() = position + direction.turnLeft()
    fun turnLeft() {
        direction--
    }

    val right get() = position + direction.turnRight()
    fun turnRight() {
        direction++
    }
}

class ScaffoldMap(private val scaffolds: List<Position>, private val vacuumRobot: VacuumRobot) {

    fun sumOfTheAlignmentParameters(): Int {
        return scaffolds.filter { isIntersection(it) }.sumOf { (i, j) -> i * j }
    }

    private fun isIntersection(position: Position) =
        position.neighbours().count { it in scaffolds } == INTERSECTION_COUNT

    fun findPath(): MutableList<String> {
        val path = mutableListOf<String>()
        var forwardCount = 0
        while (true) {
            when {
                vacuumRobot.forward in scaffolds -> {
                    forwardCount++
                    vacuumRobot.moveForward()
                }
                vacuumRobot.left in scaffolds -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "R"
                    vacuumRobot.turnLeft()
                }
                vacuumRobot.right in scaffolds -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "L"
                    vacuumRobot.turnRight()
                }
                else -> {
                    addForwardCount(path, forwardCount)
                    return path
                }
            }
        }
    }

    private fun addForwardCount(path: MutableList<String>, forwardCount: Int): Int {
        if (forwardCount > 0) {
            path += forwardCount.toString()
        }
        return 0
    }

    companion object {

        fun from(input: String): ScaffoldMap {
            var i = 0
            var j = 0
            val scaffolds = mutableListOf<Position>()
            var vacuumRobot: VacuumRobot? = null
            for (c in input) {
                when (c) {
                    '#' -> {
                        scaffolds.add(Position(i, j))
                        i++
                    }
                    '^' -> {
                        vacuumRobot = VacuumRobot(
                            Position(i, j),
                            Direction.DOWN
                        )
                        i++
                    }
                    'v' -> {
                        vacuumRobot = VacuumRobot(
                            Position(i, j),
                            Direction.UP
                        )
                        i++
                    }
                    '<' -> {
                        vacuumRobot = VacuumRobot(
                            Position(i, j),
                            Direction.RIGHT
                        )
                        i++
                    }
                    '>' -> {
                        vacuumRobot = VacuumRobot(
                            Position(i, j),
                            Direction.LEFT
                        )
                        i++
                    }
                    'X' -> {
                        error("Crashed")
                    }
                    '.' -> {
                        i++
                    }
                    '\n' -> {
                        j++
                        i = 0
                    }
                    else -> error("Unknown map character $c at $i,$j")
                }
            }
            checkNotNull(vacuumRobot) { "Robot not found" }
            return ScaffoldMap(scaffolds, vacuumRobot)
        }

        private const val INTERSECTION_COUNT = 4
    }
}

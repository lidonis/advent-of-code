package fr.lidonis.adventofcode.y2019.day17

import Direction
import Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class AftScaffoldingControlAndInformationInterface(program: String) {

    private val computer =
        IntCodeComputerFactory.buildASCIIComputer(
            program
        )

    private val scaffolds = mutableListOf<Position>()
    private lateinit var vacuumRobotPosition: Position
    private lateinit var vacuumRobotDirection: Direction
    private var scaffoldMap: ScaffoldMap

    init {
        var i = 0
        var j = 0
        computer.run()

        computer.outputs.forEach {
            when (it.toChar()) {
                '#' -> {
                    scaffolds.add(Position(i, j))
                    i++
                }
                '^' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = Direction.UP
                    i++
                }
                'v' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = Direction.DOWN
                    i++
                }
                '<' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = Direction.LEFT
                    i++
                }
                '>' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = Direction.RIGHT
                    i++
                }
                'X' -> {
                    throw IllegalStateException("Crashed")
                }
                '.' -> {
                    i++
                }
                '\n' -> {
                    j++
                    i = 0
                }
                else -> throw IllegalStateException("Unknown map character")
            }
        }
        computer.reset()
        scaffoldMap = ScaffoldMap(scaffolds, VacuumRobot(vacuumRobotPosition, vacuumRobotDirection))
    }

    fun sumOfTheAlignmentParameters() = scaffoldMap.sumOfTheAlignmentParameters()

    fun amountOfDustCollected(videoFeed: Boolean = false): Long? {
        computer.memory[0] = 2
        computer.input("A,C,A,C,B,C,B,A,C,B")
        computer.input("R,4,R,10,R,8,R,4")
        computer.input("R,4,L,12,R,6,L,12")
        computer.input("R,10,R,6,R,4")
        computer.input(if (videoFeed) "y" else "n")
        computer.run()
        println(scaffoldMap.findPath())
        return computer.outputs.pollLast()
    }

}

data class ScaffoldMap(val scaffolds: List<Position>, val vacuumRobot: VacuumRobot) {

    fun sumOfTheAlignmentParameters(): Int {
        return scaffolds.filter { isIntersection(it) }.map { (i, j) -> i * j }.sum()
    }

    private fun isIntersection(position: Position) = getNeighboursCount(position) == 4

    private fun getNeighboursCount(position: Position) =
        Direction.values().map { position.move(it) }.count { scaffolds.contains(it) }

    fun findPath(): MutableList<String> {
        val path = mutableListOf<String>()
        var forwardCount = 0
        while (true) {
            when {
                scaffolds.contains(vacuumRobot.position.move(vacuumRobot.direction)) -> {
                    forwardCount++
                    vacuumRobot.position = vacuumRobot.position.move(vacuumRobot.direction)
                }
                scaffolds.contains(vacuumRobot.position.move(vacuumRobot.direction.turnLeft())) -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "L"
                    vacuumRobot.direction--
                }
                scaffolds.contains(vacuumRobot.position.move(vacuumRobot.direction.turnRight())) -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "R"
                    vacuumRobot.direction++
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
                        vacuumRobot = VacuumRobot(Position(i, j), Direction.UP)
                        i++
                    }
                    'v' -> {
                        vacuumRobot = VacuumRobot(Position(i, j), Direction.DOWN)
                        i++
                    }
                    '<' -> {
                        vacuumRobot = VacuumRobot(Position(i, j), Direction.LEFT)
                        i++
                    }
                    '>' -> {
                        vacuumRobot = VacuumRobot(Position(i, j), Direction.RIGHT)
                        i++
                    }
                    'X' -> {
                        throw IllegalStateException("Crashed")
                    }
                    '.' -> {
                        i++
                    }
                    '\n' -> {
                        j++
                        i = 0
                    }
                    else -> throw IllegalStateException("Unknown map character")
                }
            }
            checkNotNull(vacuumRobot) { "Robot not found" }
            return ScaffoldMap(scaffolds, vacuumRobot)
        }
    }
}

class VacuumRobot(var position: Position, var direction: Direction)


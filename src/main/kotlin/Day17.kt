import java.lang.IllegalStateException

fun main() {
    val input = InputReader("day17.txt").asLineOfLongs()
    val ascii = AftScaffoldingControlAndInformationInterface(input)
    println(ascii.sumOfTheAlignmentParameters())
    println(ascii.amountOfDustCollected())
    // TODO Algorithm to split path
}

class AftScaffoldingControlAndInformationInterface(private val program: List<Long>) {

    private val computer = IntCodeComputer(program)

    private val scaffolds = mutableListOf<Position>()
    private lateinit var vacuumRobotPosition: Position
    private lateinit var vacuumRobotDirection: CardinalPoint

    init {
        var i = 0
        var j = 0
        computer.asSequence().last()

        computer.outputs.forEach {
            when (it.toChar()) {
                '#' -> {
                    scaffolds.add(Position(i, j))
                    i++
                }
                '^' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = CardinalPoint.NORTH
                    i++
                }
                'v' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = CardinalPoint.SOUTH
                    i++
                }
                '<' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = CardinalPoint.WEST
                    i++
                }
                '>' -> {
                    vacuumRobotPosition = Position(i, j)
                    vacuumRobotDirection = CardinalPoint.EAST
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
    }


    fun sumOfTheAlignmentParameters(): Int {
        return scaffolds.filter { getNeighboursCount(scaffolds, it) == 4 }.map { (i, j) -> i * j }.sum()
    }

    private fun getNeighboursCount(scaffolds: List<Position>, intersection: Position) =
        Direction.values().map { intersection.move(it) }.count { scaffolds.contains(it) }

    fun amountOfDustCollected(videoFeed: Boolean = false): Long? {
        computer[0] = 2
        computer.input("A,C,A,C,B,C,B,A,C,B")
        computer.input("R,4,R,10,R,8,R,4")
        computer.input("R,4,L,12,R,6,L,12")
        computer.input("R,10,R,6,R,4")
        computer.input(if (videoFeed) "y" else "n")
        computer.asSequence().last()

        val result = computer.outputs.last()

        computer.outputs.forEach { print(it.toChar()) }
        return result
    }

    private fun findPath(): MutableList<String> {
        val path = mutableListOf<String>()
        var forwardCount = 0
        while (true) {
            when {
                scaffolds.contains(vacuumRobotPosition.move(vacuumRobotDirection)) -> {
                    forwardCount++
                    vacuumRobotPosition = vacuumRobotPosition.move(vacuumRobotDirection)
                }
                scaffolds.contains(vacuumRobotPosition.move(vacuumRobotDirection.turnLeft())) -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "L"
                    vacuumRobotDirection--
                }
                scaffolds.contains(vacuumRobotPosition.move(vacuumRobotDirection.turnRight())) -> {
                    forwardCount = addForwardCount(path, forwardCount)
                    path += "R"
                    vacuumRobotDirection++
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

}

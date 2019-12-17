import java.lang.IllegalStateException

fun main() {
    val input = InputReader("day17.txt").asLineOfLongs()
    val ascii = AftScaffoldingControlAndInformationInterface(input)
    //println(ascii.sumOfTheAlignmentParameters())
    ascii.amountOfDustCollected()
}

class AftScaffoldingControlAndInformationInterface(private val program: List<Long>) {

    private val computer = IntCodeComputer(program)

    private val scaffolds = mutableListOf<Position>()
    private var vacuumRobotPosition = Position(0, 0)

    init {
        var i = 0
        var j = 0
        var cameraValue = computer.nextOutput()
        while (cameraValue != null) {
            print(cameraValue.toChar())
            when (cameraValue.toChar()) {
                '#' -> {
                    scaffolds.add(Position(i, j))
                    i++
                }
                '^' -> {
                    vacuumRobotPosition = Position(i, j)
                    i++
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
            cameraValue = computer.nextOutput()
        }
        computer.reset()
    }


    fun sumOfTheAlignmentParameters(): Int {
        return scaffolds.filter { getNeighboursCount(scaffolds, it) == 4 }.map { (i, j) -> i * j }.sum()
    }

    private fun getNeighboursCount(scaffolds: List<Position>, intersection: Position) =
        Direction.values().map { intersection.move(it) }.count { scaffolds.contains(it) }

    fun amountOfDustCollected(){
        computer[0] = 2
        computer.input('A'.toLong())
        computer.input(','.toLong())
        computer.input('B'.toLong())
        computer.input('\n'.toLong())
        computer.input('R'.toLong())
        computer.input('1'.toLong())
        computer.input('\n'.toLong())
        computer.input('2'.toLong())
        computer.input('\n'.toLong())
        computer.input('3'.toLong())
        computer.input('\n'.toLong())
        computer.input('y'.toLong())
        computer.input('\n'.toLong())

        var cameraValue = computer.nextOutput()
        while (cameraValue != null) {
            print(cameraValue.toChar())
            cameraValue = computer.nextOutput()
        }
    }

}
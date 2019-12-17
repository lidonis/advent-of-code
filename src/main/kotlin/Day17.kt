fun main() {
    val input = InputReader("day17.txt").asLineOfLongs()
    val ascii = AftScaffoldingControlAndInformationInterface(input)
    ascii.draw()
    println(ascii.scaffoldMap())
}

class AftScaffoldingControlAndInformationInterface(private val program: List<Long>) {

    private val computer = IntCodeComputer(program)

    fun draw() {
        var cameraValue = computer.nextOutput()
        while (cameraValue != null) {
            print(cameraValue.toChar())
            cameraValue = computer.nextOutput()
        }
        computer.reset()
    }

    fun scaffoldMap(): Int {
        val scaffolds = mutableListOf<Position>()
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
                '\n' -> {
                    j++
                    i = 0
                }
                else -> i++
            }
            cameraValue = computer.nextOutput()
        }
        return scaffolds.filter { getNeighboursCount(scaffolds, it) == 4 }.map { (i,j) -> i * j }.sum()
    }

    private fun getNeighboursCount(scaffolds: List<Position>, intersection: Position) =
        Direction.values().map { intersection.move(it) }.count { scaffolds.contains(it) }

}
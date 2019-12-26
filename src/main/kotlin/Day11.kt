import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

fun main() {
    val program = InputReader("day11.txt").asLineOfLongs()
    val robot = EmergencyHullPaintingRobot(program)
    robot.compute();
}

class EmergencyHullPaintingRobot(program: List<Long>) {
    private val computer = IntCodeComputer(program)
    private var direction = Direction.U
    private var position = Position(0, 0)
    private val panels = mutableMapOf<Position, Color>().withDefault { Color.BLACK }

    fun compute() {
        while (computer.hasNext()) {
            computer.input(panels.getValue(position).value)
            val paint = computer.nextOutput()
            requireNotNull(paint) { "No paint value" }
            paint(paint)
            computer.input(panels.getValue(position).value)
            val rotate = computer.nextOutput()
            requireNotNull(rotate) { "No rotate value" }
            rotate(rotate)
            position = position.move(direction)
        }
    }

    private fun rotate(output: Long) {
        when (output) {
            0L -> direction++
            1L -> direction--
            else -> throw IllegalArgumentException("Wrong rotation")
        }
    }

    private fun paint(output: Long) {
        val color = when (output) {
            0L -> Color.BLACK
            1L -> Color.WHITE
            else -> throw IllegalArgumentException("Paint not found")
        }
        panels[position] = color
    }

    enum class Color(val value: Long) {
        BLACK(0), WHITE(1);
    }

}
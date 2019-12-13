import java.lang.IllegalArgumentException

fun main() {
    val program = InputReader("day11.txt").asLinesOfLongs()[0]
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
            computer.inputs.add(panels.getValue(position).value)
            while (computer.hasNext() && computer.outputs.size < 2) {
                computer.next()
            }
            paint(computer.outputs[0])
            computer.inputs.add(panels.getValue(position).value)
            rotate(computer.outputs[1])
            position = position.move(direction)
            computer.outputs = mutableListOf()
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
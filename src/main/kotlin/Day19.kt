import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    val input = InputReader("day19.txt").asLineOfLongs()
    val tractorBeam = TractorBeam(input)
    println(tractorBeam.countAffected(50))
    print(tractorBeam.fit(100))
}

class TractorBeam(program: List<Long>) {
    private val beamMap = BeamMap(program)

    fun countAffected(size: Int): Int {
        return affected(0, size).count()
    }

    private fun affected(min: Int, max: Int): MutableList<Position> {
        val affected = mutableListOf<Position>()
        for (i in min until max) {
            for (j in i until max) {
                val position = Position(i, j)
                val output = beamMap[position]
                if (output == 1L) {
                    affected.add(position)
                }
            }
        }
        return affected
    }

    fun fit(size: Int) = affected(1800, 2000).asSequence().first { rightSize(it, size) }.let { it.x * 10000 + it.y }

    private fun rightSize(position: Position, size: Int) =
        beamMap[Position(position.x + size - 1, position.y)] == 1L &&
                beamMap[Position(position.x, position.y + size - 1)] == 1L

    class BeamMap(program: List<Long>) {

        private val computer = IntCodeComputerFactory.buildIOComputer(program)
        private val map = mutableMapOf<Position, Long>()

        operator fun get(position: Position) =
            map.computeIfAbsent(position) {
                computer.reset()
                computer.input(it.x.toLong())
                computer.input(it.y.toLong())
                computer.nextOutput() ?: error { "Position not found $it" }
            }

    }
}
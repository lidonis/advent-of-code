import ArcadeCabinet.Screen.Tile.*
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    val program = InputReader("day13.txt").asLineOfLongs()
    val arcadeCabinet = ArcadeCabinet(program)
    //println(arcadeCabinet.countBlock())
    arcadeCabinet.play()
}


class ArcadeCabinet(program: List<Long>) {
    private val computer = IntCodeComputerFactory.buildIOComputer(program)
    private val screen = Screen()
    private var score = 0L

    fun countBlock(): Int {
        computer.run()
        val screen = Screen()
        computer.outputs.chunked(3).forEach {
            screen[Position(it[0].toInt(), it[1].toInt())] = Screen.Tile[it[2].toInt()]
        }
        return screen.count(BLOCK)
    }

    private val scorePosition = Position(-1, 0)

    private fun frame() {
        while (computer.hasNext()) {
            val instruction = getInstruction()
            if (instruction.first != scorePosition) {
                screen[instruction.first] = Screen.Tile[instruction.second.toInt()]
            } else {
                score = instruction.second
                break
            }
        }
    }

    private fun getInstruction() =
        Position(computer.nextOutput()?.toInt()!!, computer.nextOutput()?.toInt()!!) to computer.nextOutput()!!


    fun play(): Int {
        computer.reset()
        computer.memory[0] = 2
        var frameCount = 0
        while (computer.hasNext()) {
            println("Frame $frameCount")
            frameCount++
            frame()
            screen.print()
        }
        return 0
    }

    class Screen {

        private val pixels = mutableMapOf<Position, Tile>()

        fun count(tile: Tile) = pixels.count { it.value == tile }

        fun print() {
            pixels.forEach {
                print(
                    when (it.value) {
                        EMPTY -> ' '
                        WALL -> '█'
                        BLOCK -> 'N'
                        PADDLE -> '_'
                        BALL -> 'o'
                    }
                )
                if (it.key.x == 42) {
                    println()
                }
            }
        }

        operator fun set(position: Position, tile: Tile) {
            pixels[position] = tile
        }

        enum class Tile {
            EMPTY, WALL, BLOCK, PADDLE, BALL;

            companion object {
                operator fun get(ordinal: Int) = values()[ordinal]
            }
        }

    }
}
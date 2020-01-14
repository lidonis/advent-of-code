package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class ArcadeCabinet(program: String) {
    private val computer = IntCodeComputerFactory.buildIOComputer(program)
    private val screen = Screen()
    private var score = 0L

    fun countBlock(): Int {
        computer.run()
        val screen = Screen()
        computer.outputs.chunked(GROUP_SIZE).forEach {
            screen[Position(
                it[0].toInt(),
                it[1].toInt()
            )] = Screen.Tile[it[2].toInt()]
        }
        return screen.count(Screen.Tile.BLOCK)
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
        Position(
            computer.nextOutput()?.toInt()!!,
            computer.nextOutput()?.toInt()!!
        ) to computer.nextOutput()!!

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
                        Tile.EMPTY -> ' '
                        Tile.WALL -> '█'
                        Tile.BLOCK -> '▣'
                        Tile.PADDLE -> '_'
                        Tile.BALL -> '◍'
                    }
                )
                if (it.key.x == SIZE) {
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

        companion object {
            private const val SIZE = 42
        }
    }

    companion object {
        private const val GROUP_SIZE = 3
    }
}

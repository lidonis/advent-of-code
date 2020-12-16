package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IOCodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.InputDevice
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import kotlin.math.sign

private const val INSTRUCTION_SIZE = 3
private const val BLOCK_ID = 2L
private const val PADDLE_ID = 3L
private const val BALL_ID = 4L
private val SCORE_POSITION = Position(-1, 0)

class ArcadeCabinet(program: String) {
    private val joystick = JoystickBot()
    private val computer: IOCodeComputer = IntCodeComputer(program.split(",").map(String::toLong), input = joystick)
    private var score = 0L

    fun countBlock(): Int {
        computer.reset()
        computer.run()
        val tileIds = computer.outputs.asSequence()
            .drop(2).windowed(1, INSTRUCTION_SIZE).map { it.first() }
        return tileIds.count { it == BLOCK_ID }
    }

    fun bot(): Long {
        computer.reset()
        insert2Quarters()
        while (computer.hasNext()) {
            val instruction = getInstruction()
            // no pattern matching :(
            when {
                instruction == null -> break
                instruction.first == SCORE_POSITION -> score = instruction.second
                instruction.second == PADDLE_ID -> joystick.paddleX = instruction.first.x
                instruction.second == BALL_ID -> joystick.ballX = instruction.first.x
            }
        }
        return score
    }

    private fun insert2Quarters() {
        computer.memory[0] = 2
    }

    private fun getInstruction() = computer.nextOutput()?.let { x ->
        computer.nextOutput()?.let { y ->
            computer.nextOutput()?.let { tile ->
                Position(x.toInt(), y.toInt()) to tile
            }
        }
    }

    class JoystickBot : InputDevice {
        var ballX = 0
        var paddleX = 0

        override fun read() = (ballX - paddleX).sign.toLong()

        override fun add(value: Long) {
            TODO("Should be in this interface")
        }

        override fun reset() {
            ballX = 0
            paddleX = 0
        }
    }

}

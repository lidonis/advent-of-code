package fr.lidonis.adventofcode.y2019.intcodecomputer

import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.ADDS
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.ADJUSTS_THE_RELATIVE_BASE
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.HALT_PROGRAM
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.INPUT
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.JUMP_IF_FALSE
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.JUMP_IF_TRUE
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.MULTIPLIES
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.OUTPUTS
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.STORE_IF_EQUALS
import fr.lidonis.adventofcode.y2019.intcodecomputer.Instruction.STORE_IF_LESS_THAN
import java.util.*
import java.util.concurrent.ArrayBlockingQueue

private const val MAX_CAPACITY = 100000

private const val OP_CODE_ADDS = 1
private const val OP_CODE_MULTIPLIES = 2
private const val OP_CODE_INPUT = 3
private const val OP_CODE_OUTPUTS = 4
private const val OP_CODE_JUMP_IF_TRUE = 5
private const val OP_CODE_JUMP_IF_FALSE = 6
private const val OP_CODE_STORE_IF_LESS_THAN = 7
private const val OP_CODE_STORE_IF_EQUALS = 8
private const val OP_CODE_ADJUSTS_THE_RELATIVE_BASE = 9
private const val OP_CODE_HALT_PROGRAM = 99

private const val INSTRUCTION_SIZE_NO_PARAM = 0
private const val INSTRUCTION_SIZE_ONE_PARAM = 1
private const val INSTRUCTION_SIZE_TWO_PARAMS = 2
private const val INSTRUCTION_SIZE_THREE_PARAMS = 3

private enum class Instruction(val opcode: Int, val size: Int) {
    ADDS(OP_CODE_ADDS, INSTRUCTION_SIZE_THREE_PARAMS),
    MULTIPLIES(OP_CODE_MULTIPLIES, INSTRUCTION_SIZE_THREE_PARAMS),
    INPUT(OP_CODE_INPUT, INSTRUCTION_SIZE_ONE_PARAM),
    OUTPUTS(OP_CODE_OUTPUTS, INSTRUCTION_SIZE_ONE_PARAM),
    JUMP_IF_TRUE(OP_CODE_JUMP_IF_TRUE, INSTRUCTION_SIZE_TWO_PARAMS),
    JUMP_IF_FALSE(OP_CODE_JUMP_IF_FALSE, INSTRUCTION_SIZE_TWO_PARAMS),
    STORE_IF_LESS_THAN(OP_CODE_STORE_IF_LESS_THAN, INSTRUCTION_SIZE_THREE_PARAMS),
    STORE_IF_EQUALS(OP_CODE_STORE_IF_EQUALS, INSTRUCTION_SIZE_THREE_PARAMS),
    ADJUSTS_THE_RELATIVE_BASE(OP_CODE_ADJUSTS_THE_RELATIVE_BASE, INSTRUCTION_SIZE_ONE_PARAM),
    HALT_PROGRAM(OP_CODE_HALT_PROGRAM, INSTRUCTION_SIZE_NO_PARAM);

    companion object {
        fun fromOpcode(opcode: Int) =
            values().find { it.opcode == opcode }
                ?: error("Opcode unknown")
    }
}

class IntCodeComputer(
    override val program: Iterable<Long>,
    private val output: OutputDevice = DequeOutputDevice(),
    private val input: InputDevice = QueueInputDevice()
) : ASCIICodeComputer {
    override var memory = Memory(program.toMutableList())
    private var instructionPointer = 0
    private var relativeBase = 0L

    private val currentCode: String
        get() = memory[instructionPointer].toString()

    override fun reset() {
        if (instructionPointer != 0 && memory != program) {
            instructionPointer = 0
            memory = Memory(program.toMutableList())
            relativeBase = 0L
            input.reset()
            output.reset()
        }
    }

    override fun hasNext() = memory[instructionPointer] != OP_CODE_HALT_PROGRAM.toLong()

    private val opcode get() = currentCode.takeLast(2).toInt()

    private fun read(position: Int) =
        memory[readPosition(position)]

    private fun readPosition(position: Int) =
        when (currentCode.getOrElse(currentCode.length - (position + 2)) { '0' }) {
            '0' -> memory[instructionPointer + position].toInt()
            '1' -> instructionPointer + position
            '2' -> (memory[instructionPointer + position] + relativeBase).toInt()
            else -> error("Parameter mode unknown")
        }

    private fun write(index: Int, value: Long) {
        memory[readPosition(index)] = value
    }

    override fun next(): IOCodeComputer {
        when (val instruction = Instruction.fromOpcode(opcode)) {
            ADDS -> {
                write(instruction.size, read(1) + read(2))
                instructionPointer += instruction.size + 1
            }

            MULTIPLIES -> {
                write(instruction.size, read(1) * read(2))
                instructionPointer += instruction.size + 1
            }

            INPUT -> {
                write(instruction.size, input.read())
                instructionPointer += instruction.size + 1
            }

            OUTPUTS -> {
                output.write(read(1))
                instructionPointer += instruction.size + 1
            }

            JUMP_IF_TRUE -> {
                instructionPointer = if (read(1).toBoolean()) {
                    read(2).toInt()
                } else {
                    instructionPointer + instruction.size + 1
                }
            }

            JUMP_IF_FALSE -> {
                instructionPointer = if (!read(1).toBoolean()) {
                    read(2).toInt()
                } else {
                    instructionPointer + instruction.size + 1
                }
            }

            STORE_IF_LESS_THAN -> {
                write(instruction.size, (read(1) < read(2)).toLong())
                instructionPointer += instruction.size + 1
            }

            STORE_IF_EQUALS -> {
                write(instruction.size, (read(1) == read(2)).toLong())
                instructionPointer += instruction.size + 1
            }

            ADJUSTS_THE_RELATIVE_BASE -> {
                relativeBase += read(1)
                instructionPointer += instruction.size + 1
            }

            HALT_PROGRAM -> {
                error("Program halted")
            }
        }
        return this
    }

    override fun run() {
        while (hasNext()) {
            next()
        }
    }

    override val outputs: Deque<Long>
        get() = output.values

    override fun input(value: Long) {
        input.add(value)
    }

    override fun nextOutput(): Long? {
        var signal: Long? = null
        while (signal == null && hasNext()) {
            next()
            signal = outputs.poll()
        }
        return signal
    }

    override fun tryNextOutput(maxTries: Int): Long? {
        repeat(maxTries) {
            next()
            val output = outputs.poll()
            if (output != null) {
                return output
            }
        }
        return null
    }
}

fun Long.toBoolean() = this != 0L
fun Boolean.toLong() = if (this) 1L else 0L

class DequeOutputDevice : OutputDevice {

    override var values = ArrayDeque<Long>()

    override fun write(value: Long) {
        values.add(value)
    }

    override fun reset() {
        values = ArrayDeque()
    }
}

class QueueInputDevice : InputDevice {

    var values = ArrayBlockingQueue<Long>(MAX_CAPACITY)

    override fun read(): Long = values.poll()

    override fun add(value: Long) {
        values.add(value)
    }

    override fun reset() {
        values = ArrayBlockingQueue(MAX_CAPACITY)
    }

}

class Memory(private val list: MutableList<Long>) : MutableList<Long> by list {
    override operator fun set(index: Int, element: Long): Long {
        val size = index + 1
        while (list.size < size) {
            list.add(0L)
        }
        list[index] = element
        return element
    }

    override operator fun get(index: Int) = list.getOrElse(index) { 0L }
}

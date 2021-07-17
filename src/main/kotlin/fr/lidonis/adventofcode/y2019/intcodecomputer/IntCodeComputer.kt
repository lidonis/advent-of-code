package fr.lidonis.adventofcode.y2019.intcodecomputer

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

private const val END_PROGRAM = 99L
private const val MAX_CAPACITY = 100000

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
        if (needReset()) {
            instructionPointer = 0
            memory = Memory(program.toMutableList())
            relativeBase = 0L
            input.reset()
            output.reset()
        }
    }

    private fun needReset() = instructionPointer != 0 && memory != program

    override fun hasNext() = memory[instructionPointer] != END_PROGRAM

    private fun incrementInstructionPointer(value: Int): () -> Unit = {
        instructionPointer += value
    }

    private val opcode get() = currentCode.takeLast(2).toInt()

    private fun parameterMode(index: Int) =
        currentCode.getOrElse(currentCode.length - index) { '0' }

    private fun read(position: Int) =
        memory[readPosition(position)]

    private fun readPosition(position: Int) =
        when (parameterMode(position + 2)) {
            '0' -> memory[instructionPointer + position].toInt()
            '1' -> instructionPointer + position
            '2' -> (memory[instructionPointer + position] + relativeBase).toInt()
            else -> error("Parameter mode unknown")
        }

    private fun write(index: Int, value: Long) {
        val position = readPosition(index)
        memory[position] = value
        incrementInstructionPointer(index + 1)()
    }

    override fun next(): IOCodeComputer {
        when (opcode) {
            1 -> {
                write(3, read(1) + read(2))
            }
            2 -> {
                write(3, read(1) * read(2))
            }
            3 -> {
                write(1, input.read())
            }
            4 -> {
                output.write(read(1));
                incrementInstructionPointer(2)()
            }
            5 -> {

                instructionPointer = if (read(1) != 0L) {
                    read(2).toInt()
                } else {
                    instructionPointer + 3
                }

            }
            6 -> {
                instructionPointer = if (read(1) == 0L) {
                    read(2).toInt()
                } else {
                    instructionPointer + 3
                }
            }
            7 -> {
                write(
                    3,
                    if ((read(1) < read(2))) 1L else 0L
                )
            }
            8 -> {
                write(
                    3,
                    if ((read(1) == read(2))) 1L else 0L
                )
            }
            9 -> {
                relativeBase += read(1); incrementInstructionPointer(2)()
            }
            99 -> error("Program halted")
            else -> error("Opcode unknown")
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
        (0 until maxTries).forEach { _ ->
            next()
            val output = outputs.poll()
            if (output != null) {
                return output
            }
        }
        return null
    }
}

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

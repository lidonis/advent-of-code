package fr.lidonis.adventofcode.y2019.intcodecomputer

import java.util.*
import java.util.concurrent.ArrayBlockingQueue

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

    override fun hasNext() = memory[instructionPointer] != 99L

    private fun getActions(): MutableList<() -> Unit> {
        val actions = mutableListOf<() -> Unit>()
        when (opcode) {
            1 -> {
                actions.addAll(
                    listOf(
                        {
                            write(
                                3, firstParameter() + secondParameter(), parameterMode(5)
                            )
                        },
                        incrementInstructionPointer(4)
                    )
                )
            }
            2 -> {
                actions.addAll(
                    listOf(
                        {
                            write(
                                3, firstParameter() * secondParameter(), parameterMode(5)
                            )
                        }
                        , incrementInstructionPointer(4)
                    )
                )
            }
            3 -> {
                actions.addAll(
                    listOf(
                        { write(1, input.read(), parameterMode(3)) },
                        incrementInstructionPointer(2)
                    )
                )
            }
            4 -> {
                actions.add {
                    output.write(read(1, parameterMode(3)))
                }

                actions.add(incrementInstructionPointer(2))
            }
            5 -> {
                instructionPointer = if (firstParameter() != 0L) {
                    secondParameter().toInt()
                } else {
                    instructionPointer + 3
                }
            }
            6 -> {
                instructionPointer = if (firstParameter() == 0L) {
                    secondParameter().toInt()
                } else {
                    instructionPointer + 3
                }

            }
            7 -> {
                actions.add {
                    write(
                        3,
                        if ((firstParameter() < secondParameter())) 1L else 0L,
                        parameterMode(5)
                    )
                }
                actions.add(incrementInstructionPointer(4))
            }
            8 -> {
                actions.add {
                    write(
                        3,
                        if ((firstParameter() == secondParameter())) 1L else 0L,
                        parameterMode(5)
                    )
                }
                actions.add(incrementInstructionPointer(4))
            }
            9 -> {
                actions.add { relativeBase += firstParameter() }
                actions.add(incrementInstructionPointer(2))
            }
            99 -> throw IllegalStateException("Program halted")
            else -> throw IllegalArgumentException("Opcode unknown")
        }
        return actions
    }


    private fun incrementInstructionPointer(value: Int): () -> Unit = {
        instructionPointer += value
    }

    private fun firstParameter() = read(1, parameterMode(3))

    private fun secondParameter() = read(2, parameterMode(4))

    private val opcode get() = currentCode.takeLast(2).toInt()

    private fun parameterMode(index: Int) =
        currentCode.getOrElse(currentCode.length - index) { '0' }

    private fun read(position: Int, parameterMode: Char) =
        memory[readPosition(position, parameterMode)]

    private fun readPosition(position: Int, parameterMode: Char) =
        when (parameterMode) {
            '0' -> memory[instructionPointer + position].toInt()
            '1' -> instructionPointer + position
            '2' -> (memory[instructionPointer + position] + relativeBase).toInt()
            else -> throw IllegalArgumentException("Parameter mode unknown")
        }


    private fun write(index: Int, value: Long, parameterMode: Char) {
        val position = readPosition(index, parameterMode)
        memory[position] = value
    }

    override fun next(): IOCodeComputer {
        getActions().forEach { it() }
        return this
    }

    override fun run() {
        while (hasNext()) {
            next()
        }
    }

    override val outputs: Deque<Long>
        get() {
            return output.values
        }

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
        for (i in 0 until maxTries) {
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
    var values = ArrayBlockingQueue<Long>(100000)

    override fun read(): Long = values.poll()
    override fun add(value: Long) {
        values.add(value)
    }

    override fun reset() {
        values = ArrayBlockingQueue(100000)
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
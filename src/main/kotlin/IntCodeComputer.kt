import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit

class IntCodeComputer(private val program: List<Long>) :
    Iterator<IntCodeComputer> {

    private var instructionPointer = 0L
    private var relativeBase = 0L
    var inputs = ArrayBlockingQueue<Long>(1000)
    var outputs = ArrayBlockingQueue<Long>(1000)
    var memory = Memory(program.toMutableList())

    fun reset() {
        instructionPointer = 0
        relativeBase = 0
        inputs = ArrayBlockingQueue(1000)
        outputs = ArrayBlockingQueue(1000)
        memory = Memory(program.toMutableList())
    }

    private fun computeStep() {
        getActions().forEach { it() }
    }

    private val currentCode: String
        get() = memory[instructionPointer].toString()

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
                        { write(1, inputs.poll(50, TimeUnit.MILLISECONDS) ?: -1, parameterMode(3)) },
                        incrementInstructionPointer(2)
                    )
                )
            }
            4 -> {
                actions.add {
                    outputs.add(read(1, parameterMode(3)))
                }

                actions.add(incrementInstructionPointer(2))
            }
            5 -> {
                instructionPointer = if (firstParameter() != 0L) {
                    secondParameter()
                } else {
                    instructionPointer + 3
                }
            }
            6 -> {
                instructionPointer = if (firstParameter() == 0L) {
                    secondParameter()
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

    private fun incrementInstructionPointer(value: Long): () -> Unit = {
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
            '0' -> memory[instructionPointer + position]
            '1' -> instructionPointer + position
            '2' -> memory[instructionPointer + position] + relativeBase
            else -> throw IllegalArgumentException("Parameter mode unknown")
        }


    private fun write(index: Int, value: Long, parameterMode: Char) {
        val position = readPosition(index, parameterMode)
        memory[position] = value
    }

    override fun hasNext() = memory[instructionPointer] != 99L

    override fun next()
            : IntCodeComputer {
        computeStep()
        return this
    }

    fun input(value: Long) {
        inputs.add(value)
    }

    fun input(value: String) {
        (value + "\n").chars().forEach { input(it.toLong()) }
    }

    operator fun set(i: Long, value: Long) {
        memory[i] = value
    }

    fun nextOutput(): Long? {
        var signal: Long? = null
        while (signal == null && hasNext()) {
            next()
            signal = outputs.poll()
        }
        return signal
    }

    fun tryNextOutput(maxTries: Int): Long? {
        for (i in 0 until maxTries) {
            next()
            val output = outputs.poll()
            if (output != null) {
                return output
            }
        }
        return null
    }


    class Memory(private val list: MutableList<Long>) : MutableList<Long> by list {
        operator fun set(index: Long, value: Long) {
            val size = (index + 1).toInt()
            while (list.size < size) {
                list.add(0L)
            }
            list[index.toInt()] = value
        }

        operator fun get(index: Long) = list.getOrElse(index.toInt()) { 0L }
    }

}


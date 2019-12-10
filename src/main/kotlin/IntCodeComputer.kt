import java.util.*

class IntCodeComputer(private val program: List<Long>, var inputs: MutableList<Long> = mutableListOf()) :
    Iterator<List<Long>> {

    private var memory = Memory(program)
    private var instructionPointer = 0L
    private var currentInput = 0
    private var relativeBase = 0L

    var outputs = mutableListOf<Long>()

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
                        { write(1, inputs[currentInput++], parameterMode(3)) },
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
                        (firstParameter() < secondParameter()).toLong(),
                        parameterMode(5)
                    )
                }
                actions.add(incrementInstructionPointer(4))
            }
            8 -> {
                actions.add {
                    write(
                        3,
                        (firstParameter() == secondParameter()).toLong(),
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
            : List<Long> {
        computeStep()
        return memory.memory
    }

    fun input(noun: Long, verb: Long) {
        memory[1] = noun
        memory[2] = verb
    }

    fun reset() {
        memory = Memory(program)
        instructionPointer = 0
        currentInput = 0
        relativeBase = 0
        outputs = mutableListOf()
    }

    class Memory(
        program: List<Long>
    ) {
        internal var memory: ArrayList<Long> =
            program.toMutableList() as ArrayList<Long>

        operator fun set(index: Long, value: Long) {
            memory.ensureSize((index + 1).toInt())
            memory[index.toInt()] = value
        }

        operator fun get(index: Long) = memory.getOrElse(index.toInt()) { 0L }
    }

}

fun Boolean.toLong() = if (this) 1L else 0L

fun ArrayList<Long>.ensureSize(size: Int) {
    this.ensureCapacity(size)
    while (this.size < size) {
        this.add(0L)
    }
}

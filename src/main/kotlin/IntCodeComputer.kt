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

    private fun getActions(): MutableList<() -> Unit> {
        val code = memory[instructionPointer].toString()
        val actions = mutableListOf<() -> Unit>()
        when (opcode(code)) {
            1 -> {
                actions.add { write(3, firstParameter(code) + secondParameter(code), parameterMode(code, 5)) }
                actions.add { instructionPointer += 4 }
            }
            2 -> {
                actions.add { write(3, firstParameter(code) * secondParameter(code), parameterMode(code, 5)) }
                actions.add { instructionPointer += 4 }
            }
            3 -> {
                actions.add { write(1, inputs[currentInput++], parameterMode(code, 3)) }
                actions.add { instructionPointer += 2 }
            }
            4 -> {
                actions.add {
                    outputs.add(read(1, parameterMode(code, 3)))
                }

                actions.add {
                    instructionPointer += 2
                }
            }
            5 -> {
                if (firstParameter(code) != 0L) {
                    actions.add {
                        instructionPointer = secondParameter(code)
                    }
                } else {
                    actions.add {
                        instructionPointer += 3
                    }
                }
            }
            6 -> {
                if (firstParameter(code) == 0L) {
                    actions.add {
                        instructionPointer = secondParameter(code)
                    }
                } else {
                    actions.add {
                        instructionPointer += 3
                    }
                }
            }
            7 -> {
                actions.add {
                    write(
                        3,
                        (firstParameter(code) < secondParameter(code)).toLong(),
                        parameterMode(code, 5)
                    )
                }
                actions.add {
                    instructionPointer += 4
                }
            }
            8 -> {
                actions.add {
                    write(
                        3,
                        (firstParameter(code) == secondParameter(code)).toLong(),
                        parameterMode(code, 5)
                    )
                }
                actions.add {
                    instructionPointer += 4
                }
            }
            9 -> {
                actions.add { relativeBase += firstParameter(code) }
                actions.add {
                    instructionPointer += 2
                }
            }
            99 -> throw IllegalStateException("Program halted")
            else -> throw IllegalArgumentException("Opcode unknown")
        }
        return actions
    }

    private fun firstParameter(code: String) = read(1, parameterMode(code, 3))

    private fun secondParameter(code: String) = read(2, parameterMode(code, 4))

    private fun opcode(code: String) = code.takeLast(2).toInt()

    private fun parameterMode(code: String, index: Int) =
        code.getOrElse(code.length - index) { '0' }

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

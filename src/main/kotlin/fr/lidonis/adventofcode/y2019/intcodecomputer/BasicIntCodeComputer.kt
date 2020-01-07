package fr.lidonis.adventofcode.y2019.intcodecomputer

class BasicIntCodeComputer(override val program: Iterable<Long>) : CodeComputer {
    override var memory = program.toMutableList()
    private var instructionPointer = 0

    private val currentCode: String
        get() = memory[instructionPointer].toString()

    override fun reset() {
        if (needReset()) {
            instructionPointer = 0
            memory = program.toMutableList()
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
            else -> throw IllegalArgumentException("Parameter mode unknown")
        }


    private fun write(index: Int, value: Long, parameterMode: Char) {
        val position = readPosition(index, parameterMode)
        memory[position] = value
    }

    override fun next(): CodeComputer {
        getActions().forEach { it() }
        return this
    }

    override fun run() {
        while (hasNext()) {
            next()
        }
    }
}

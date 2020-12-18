package fr.lidonis.adventofcode.y2020.day8

import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.ACC
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.JMP
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.NOP
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.valueOf

private const val OPERATION_SIZE = 3

class HandheldGameConsole(program: List<String>) {

    private val instructions = program.map {
        Instruction(
            valueOf(it.take(OPERATION_SIZE).toUpperCase()),
            it.drop(OPERATION_SIZE + 1).toInt()
        )
    }

    fun run(): Int {
        var global = 0
        var lineIndex = LineIndex(max = instructions.size)
        while (!lineIndex.overflow) {
            val instruction = instructions[lineIndex.current]
            when (instruction.operation) {
                ACC -> {
                    global += instruction.argument
                    lineIndex++
                }
                JMP -> lineIndex += instruction.argument
                NOP -> lineIndex++
            }
            if (lineIndex.looped) throw InfiniteLoopException(global)
        }
        return global
    }

    enum class Operation {
        ACC, JMP, NOP
    }

    private data class Instruction(val operation: Operation, val argument: Int)

    private class LineIndex(
        val max: Int,
        val current: Int = 0,
        private val history: Set<Int> = emptySet()
    ) {
        operator fun plus(value: Int) = LineIndex(max, current + value, history + current)

        operator fun inc() = plus(1)

        val looped = current in history

        val overflow = current >= max
    }

    data class InfiniteLoopException(val value: Int) : Throwable()
}

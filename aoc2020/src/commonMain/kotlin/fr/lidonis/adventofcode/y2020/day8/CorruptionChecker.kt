package fr.lidonis.adventofcode.y2020.day8

import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.JMP
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.Operation.NOP

class CorruptionChecker(private val program: List<String>) {

    fun run() = swap(NOP, JMP) ?: swap(JMP, NOP)

    private fun swap(from: Operation, to: Operation) = swap(from.name.lowercase(), to.name.lowercase())

    private fun swap(from: String, to: String): Int? {
        for ((i, line) in program.withIndex()) {
            if (line.startsWith(from)) {
                HandheldGameConsole(swapProgram(from, to, i)).run().onSuccess { return it }
            }
        }
        return null
    }

    private fun swapProgram(from: String, to: String, lineIndex: Int) = sequence {
        for ((i, line) in program.withIndex()) {
            yield(if (lineIndex == i) line.replace(from, to) else line)
        }
    }.toList()
}

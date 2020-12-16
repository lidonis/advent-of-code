package fr.lidonis.adventofcode.y2020.day8

class CorruptionChecker(private val program: List<String>) {

    fun run() = swap("nop", "jmp") ?: swap("jmp", "nop")

    private fun swap(from: String, to: String): Int? {
        return program.mapIndexedNotNull { i, line ->
            if (line.startsWith(from)) tryProgram(swapProgram(from, to, i)) else null
        }.firstOrNull()
    }

    private fun swapProgram(from: String, to: String, lineIndex: Int) = program.mapIndexed { i, line ->
        if (lineIndex == i) line.replace(from, to) else line
    }

    private fun tryProgram(program: List<String>) = try {
        HandheldGameConsole(program).run()
    } catch (e: HandheldGameConsole.InfiniteLoopException) {
        null
    }
}

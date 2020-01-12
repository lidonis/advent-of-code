package fr.lidonis.adventofcode.y2019.day21

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class SpringDroid(program: String) {
    private val computer =
        IntCodeComputerFactory.buildASCIIComputer(
            program
        )

    fun executeSpringScript(walkProgram: String, printOutput: Boolean = false): Long? {
        computer.reset()
        computer.input(walkProgram)
        computer.run()
        val result = computer.outputs.pollLast()
        if (printOutput) {
            computer.outputs.forEach { print(it.toChar()) }
        }
        return result
    }

    fun walk() =
        executeSpringScript(
            """ 
            OR A J
            AND C J
            NOT J J
            AND D J
            WALK
            """.trimIndent(),
            true
        )

    fun run() =
        executeSpringScript(
            """
            OR A J
            AND B J
            AND C J
            NOT J J
            AND D J
            OR E T
            OR H T
            AND T J
            RUN
            """.trimIndent(),
            true
        )
}
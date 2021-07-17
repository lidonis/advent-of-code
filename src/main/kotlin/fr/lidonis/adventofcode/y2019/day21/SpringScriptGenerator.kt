package fr.lidonis.adventofcode.y2019.day21

import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.common.math.pow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object SpringScriptGenerator {

    private val commands = setOf("AND", "OR", "NOT")
    private val walkReadRegisters = ('A'..'D') + 'T' + 'J'
    private val runReadRegisters = ('E'..'H') + 'T' + 'J'
    private val writeRegisters = setOf('T', 'J')
    private val nbWalkInstruction = commands.size * walkReadRegisters.size * writeRegisters.size
    private val nbRunInstruction = commands.size * walkReadRegisters.size * writeRegisters.size

    fun bruteForceWalk() {
        for (i in 2..3) {
            bruteForceDroid(generateWalkScript(i))
        }
    }

    fun bruteForceRun() {
        for (i in 2..4) {
            bruteForceDroid(generateRunScript(i))
        }
    }

    private fun bruteForceDroid(sequence: Sequence<String>) {
        val program = InputReader("input/y2019/day21.txt").text()
        runBlocking {
            for (springScript in sequence) {
                launch(Dispatchers.Default) {
                    val result = SpringDroid(program).executeSpringScript(springScript)
                    if (result != null && result > 10) {
                        println(springScript)
                        println(result)
                    }
                }
            }
        }
    }

    private fun generateWalkScript(maxIteration: Int) = sequence {
        val generated = generateWalkInstruction()
            .toList()
        val indexesList = (0 until nbWalkInstruction.pow(maxIteration)).map { int ->
            int.toString(nbWalkInstruction).padStart(maxIteration, '0')
        }.map { it.map(Character::getNumericValue) }
        for (indexes in indexesList) {
            var currentInstruction = ""
            for (i in indexes) {
                currentInstruction += generated[i] + "\n"
            }
            yield(currentInstruction + "WALK")
        }
    }

    private fun generateWalkInstruction() = sequence {
        yieldAll(commands.flatMap { command ->
            walkReadRegisters.flatMap { readRegister ->
                writeRegisters.map { writeRegister ->
                    "$command $readRegister $writeRegister"
                }
            }
        })
    }

    private fun generateRunScript(maxIteration: Int) = sequence {
        val generated = generateRunInstruction()
            .toList()
        val indexesList = (0 until nbRunInstruction.pow(maxIteration)).map { int ->
            int.toString(nbRunInstruction).padStart(maxIteration, '0')
        }.map { it.map(Character::getNumericValue) }
        for (indexes in indexesList) {
            var currentInstruction = "\n"
            for (i in indexes) {
                currentInstruction += generated[i] + "\n"
            }
            yield(
                """
                OR A J
                AND B J
                AND C J
                NOT J J
                AND D J
                """.trimIndent() +
                        currentInstruction + "RUN"
            )
        }
    }

    private fun generateRunInstruction() = sequence {
        yieldAll(commands.flatMap { command ->
            runReadRegisters.flatMap { readRegister ->
                writeRegisters.map { writeRegister ->
                    "$command $readRegister $writeRegister"
                }
            }
        })
    }
}

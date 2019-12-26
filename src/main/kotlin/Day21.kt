import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.math.pow

fun main() {
    val program = InputReader("day21.txt").asLineOfLongs()
    val droid = SpringDroid(program)
    println(droid.walk())
    println(droid.run())
    bruteForceRun()
}


fun bruteForceWalk() {
    for (i in 2..3) {
        bruteForceDroid(SpringScriptGenerator.generateWalkScript(i))
    }
}

fun bruteForceRun() {
    for (i in 2..4) {
        val sequence = SpringScriptGenerator.generateRunScript(i)
        bruteForceDroid(sequence)
        //sequence.forEach(::println)
    }
}

fun bruteForceDroid(sequence: Sequence<String>) {
    val program = InputReader("day21.txt").asLineOfLongs()
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

class SpringDroid(program: List<Long>) {
    private val computer = IntCodeComputer(program)

    fun executeSpringScript(walkProgram: String, printOutput: Boolean = false): Long? {
        computer.reset()
        computer.input(walkProgram)
        computer.asSequence().last()
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

object SpringScriptGenerator {

    private val commands = setOf("AND", "OR", "NOT")
    private val walkReadRegisters = ('A'..'D') + 'T' + 'J'
    private val runReadRegisters = ('E'..'H') + 'T' + 'J'
    private val writeRegisters = setOf('T', 'J')
    private val nbWalkInstruction = commands.size * walkReadRegisters.size * writeRegisters.size
    private val nbRunInstruction = commands.size * walkReadRegisters.size * writeRegisters.size

    fun generateWalkScript(maxIteration: Int) = sequence {
        val generated = generateWalkInstruction().toList()
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

    fun generateRunScript(maxIteration: Int) = sequence {
        val generated = generateRunInstruction().toList()
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
                """.trimIndent()
                + currentInstruction + "RUN"
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

fun Int.pow(i: Int) = this.toDouble().pow(i).toInt()
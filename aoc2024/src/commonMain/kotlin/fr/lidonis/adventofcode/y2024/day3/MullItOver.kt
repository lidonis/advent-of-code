package fr.lidonis.adventofcode.y2024.day3

private val INSTRUCTION_REGEX = """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".toRegex()

class MullItOver(program: String) {

    val instructions = parseInstructions(program)

    fun part1() = instructions.sumOf { calculateSum(it) }

    fun part2() = calculateConditionalSum()

    private fun parseInstructions(program: String) =
        INSTRUCTION_REGEX.findAll(program)
            .map(MatchResult::destructured)
            .map { (instruction, x, y) -> determineInstruction(instruction, x, y) }
            .toList()

    private fun determineInstruction(instruction: String, x: String, y: String): Instruction {
        return when {
            instruction.startsWith("mul") -> Instruction.Mul(x.toInt(), y.toInt())
            instruction == "do()" -> Instruction.Do
            instruction == "don't()" -> Instruction.Dont
            else -> error("Unknown instruction: $instruction")
        }
    }

    private fun calculateSum(instruction: Instruction) = when (instruction) {
        is Instruction.Mul -> instruction.calc()
        else -> 0
    }

    private fun calculateConditionalSum() = instructions.fold(0 to true) { acc, instruction ->
        val (sum, enabled) = acc
        when (instruction) {
            is Instruction.Mul if enabled -> sum + instruction.calc() to true
            is Instruction.Mul -> acc
            Instruction.Do -> sum to true
            Instruction.Dont -> sum to false
        }
    }.first
}

sealed interface Instruction {
    data class Mul(val x: Int, val y: Int) : Instruction {
        fun calc() = x * y
    }
    data object Do : Instruction
    data object Dont : Instruction
}
package fr.lidonis.adventofcode.y2024.day3

private val regex = """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".toRegex()

class MullItOver(program: String) {

    val instructions = regex.findAll(program)
        .map(MatchResult::destructured)
        .map { (instruction, x, y) ->
            when {
                instruction.startsWith("mul") -> Instruction.Mul(x.toInt(), y.toInt())
                instruction == "do()" -> Instruction.Do
                instruction == "don't()" -> Instruction.Dont
                else -> error("Unknown instruction: $instruction")
            }
        }
        .toList()

    fun part1() =
        instructions.sumOf {
            when (it) {
                is Instruction.Mul -> it.calc()
                else -> 0
            }
        }

    fun part2() =
        instructions.fold(0 to true) { acc, instruction ->
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

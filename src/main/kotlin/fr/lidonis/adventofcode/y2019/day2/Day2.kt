package fr.lidonis.adventofcode.y2019.day2

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("The value at position 0 after the program halts is ${Day2.part1()}")
    println("100 * noun + verb to produce 19690720 is ${Day2.part2()}")
}

private const val DAY = 2

object Day2 : AdventOfCode2019(DAY) {

    private const val NOUN = 12L
    private const val VERB = 2L

    override fun part1() = runNounVerb(NOUN, VERB)

    private const val MAX_INPUT = 99L
    private const val SEARCHED_OUTPUT = 19690720L
    private const val MULTIPLIER = 100

    override fun part2(): Long {
        for (noun in 0..MAX_INPUT) {
            for (verb in 0..MAX_INPUT) {
                if (runNounVerb(noun, verb) == SEARCHED_OUTPUT) return MULTIPLIER * noun + verb
                computer.reset()
            }
        }
        throw IllegalArgumentException("No inputs found")
    }

    private fun runNounVerb(noun: Long, verb: Long) =
        computer.run {
            reset()
            memory[1] = noun
            memory[2] = verb
            run()
            memory[0]
        }

    private val computer = IntCodeComputerFactory.buildBasicComputer(input().text())
}

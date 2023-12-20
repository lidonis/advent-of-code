package fr.lidonis.adventofcode.y2019.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 2

@Suppress("unused")
object Day2 : AdventOfCode2019(DAY) {

    private const val NOUN = 12L
    private const val VERB = 2L
    private const val MAX_INPUT = 99L
    private const val SEARCHED_OUTPUT = 19690720L
    private const val MULTIPLIER = 100

    private val input = input().readText()

    @Answer("4138658")
    override fun part1() = IntCodeComputerFactory.buildBasicComputer(input).runNounVerb(NOUN, VERB)

    @Answer("7264")
    override fun part2(): Long {
        val computer = IntCodeComputerFactory.buildBasicComputer(input)
        for (noun in 0..MAX_INPUT) {
            for (verb in 0..MAX_INPUT) {
                if (computer.runNounVerb(noun, verb) == SEARCHED_OUTPUT) return MULTIPLIER * noun + verb
            }
        }
        error("No inputs found")
    }

    private fun IntCodeComputer.runNounVerb(noun: Long, verb: Long) = run {
        reset()
        memory[1] = noun
        memory[2] = verb
        run()
        memory[0]
    }
}

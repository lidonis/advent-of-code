package fr.lidonis.adventofcode.y2019.day2

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

const val DAY = 2

object Day2 : AdventOfCode2019(DAY) {

    private const val NOUN = 12L
    private const val VERB = 2L
    private const val MAX_INPUT = 99L
    private const val SEARCHED_OUTPUT = 19690720L
    private const val MULTIPLIER = 100

    private val computer = IntCodeComputerFactory.buildBasicComputer(input().text())

    override fun part1() = runNounVerb(NOUN, VERB)

    override fun part2(): Long {
        for (noun in 0..MAX_INPUT) {
            for (verb in 0..MAX_INPUT) {
                if (runNounVerb(noun, verb) == SEARCHED_OUTPUT) return MULTIPLIER * noun + verb
            }
        }
        error("No inputs found")
    }

    private fun runNounVerb(noun: Long, verb: Long) =
        computer.run {
            reset()
            memory[1] = noun
            memory[2] = verb
            run()
            memory[0]
        }
}

package fr.lidonis.adventofcode.y2019.day2

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.CodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("The value at position 0 after the program halts is ${Day2.part1()}")
    println("100 * noun + verb to produce 19690720 is ${Day2.part2()}")
}

object Day2 : AdventOfCode2019(2) {

    override fun part1(): Long {
        return runNounVerb(computer, 12, 2)
    }

    override fun part2(): Long {
        for (noun in 0..99L) {
            for (verb in 0..99L) {
                if (runNounVerb(computer, noun, verb) == 19690720L) return 100 * noun + verb
                computer.reset()
            }
        }
        throw IllegalArgumentException("No inputs found")
    }


    private fun runNounVerb(computer: CodeComputer, noun: Long, verb: Long) =
        computer.run {
            reset()
            memory[1] = noun
            memory[2] = verb
            run()
            memory[0]
        }

    private val computer by lazy {
        IntCodeComputerFactory.buildBasicComputer(input.asLineOfLongs())
    }

}


package fr.lidonis.adventofcode.y2019.day2

import InputReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

fun main() {
    println("The value at position 0 after the program halts is ${Day2.part1()}")
    println("100 * noun + verb to produce 19690720 is ${Day2.part2()}")
}

object Day2 {
    fun part1(): Long {
        val computer =
            IntCodeComputer(InputReader("day2.txt").asLineOfLongs())
        return runNounVerb(computer, 12, 2)
    }

    private fun runNounVerb(computer: IntCodeComputer, noun: Long, verb: Long): Long {
        computer[1] = noun
        computer[2] = verb
        return computer.run()[0]
    }

    fun part2(): Long {
        val computer =
            IntCodeComputer(InputReader("day2.txt").asLineOfLongs())
        for (noun in 0..99L) {
            for (verb in 0..99L) {
                if (runNounVerb(computer, noun, verb) == 19690720L) return 100 * noun + verb
                computer.reset()
            }
        }
        throw IllegalArgumentException("No inputs found")
    }

}


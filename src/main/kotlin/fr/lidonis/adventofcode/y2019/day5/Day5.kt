package fr.lidonis.adventofcode.y2019.day5

import InputReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("Diagnostic code for the program is ${Day5.part1()}")
    println("Diagnostic code for system ID 5 is ${Day5.part2()}")
}

object Day5 {

    fun part1(): Long = computer().run {
        input(1)
        run()
        val diagnosticCode = outputs.pollLast()
        check(outputs.all { it == 0L })
        diagnosticCode
    }

    fun part2() = computer().run {
        input(5)
        nextOutput()
    }

    private fun computer() = IntCodeComputerFactory.buildIOComputer(InputReader("day5.txt").asLineOfLongs())
}
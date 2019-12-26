package fr.lidonis.adventofcode.y2019.day5

import InputReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

fun main() {
    println("Diagnostic code for the program is ${Day5.part1()}")
    println("Diagnostic code for system ID 5 is ${Day5.part2()}")
}

object Day5 {

    fun part1(): Long {
        val computer = computer()
        computer.input(1)
        computer.run()
        val diagnosticCode = computer.outputs.pollLast()
        check(computer.outputs.all { it == 0L })
        return diagnosticCode
    }

    fun part2(): Long? {
        val computer = computer()
        computer.input(5)
        return computer.nextOutput()
    }

    private fun computer() = IntCodeComputer(InputReader("day5.txt").asLineOfLongs())
}
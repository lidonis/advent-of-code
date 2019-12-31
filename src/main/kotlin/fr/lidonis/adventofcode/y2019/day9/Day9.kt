package fr.lidonis.adventofcode.y2019.day9

import InputReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("The BOOST keycode is ${Day9.part1()}")
    println("The coordinates of the distress signal are ${Day9.part2()}")
}

object Day9 {

    fun part1() = compute(1)

    fun part2() = compute(2)

    private fun compute(input: Long) = computer().run {
        input(input)
        nextOutput()
    }

    private fun computer() = IntCodeComputerFactory.buildIOComputer(InputReader("day9.txt").asLineOfLongs())
}
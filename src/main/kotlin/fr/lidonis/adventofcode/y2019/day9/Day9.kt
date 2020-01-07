package fr.lidonis.adventofcode.y2019.day9

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("The BOOST keycode is ${Day9.part1()}")
    println("The coordinates of the distress signal are ${Day9.part2()}")
}

object Day9 : AdventOfCode2019(9) {

    override fun part1() = compute(1) ?: error("No boost code")
    override fun part2() = compute(2) ?: error("No distress signal")

    private fun compute(input: Long) = computer.run {
        reset()
        input(input)
        nextOutput()
    }

    private val computer by lazy {
        IntCodeComputerFactory.buildIOComputer(input.asLineOfLongs())
    }
}
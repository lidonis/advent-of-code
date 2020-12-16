package fr.lidonis.adventofcode.y2019.day9

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 9

object Day9 : AdventOfCode2019(DAY) {

    override fun part1() = compute(1) ?: error("No boost code")
    override fun part2() = compute(2) ?: error("No distress signal")

    private fun compute(input: Long) = computer.run {
        reset()
        input(input)
        nextOutput()
    }

    private val computer = IntCodeComputerFactory.buildIOComputer(input().text())
}

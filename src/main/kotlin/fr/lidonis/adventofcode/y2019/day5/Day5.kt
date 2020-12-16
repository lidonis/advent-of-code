package fr.lidonis.adventofcode.y2019.day5

import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 5

object Day5 : AdventOfCode2019(DAY) {

    override fun part1(): Long = computer.run {
        reset()
        input(1)
        run()
        val diagnosticCode = outputs.pollLast()
        check(outputs.all { it == 0L })
        diagnosticCode
    }

    private const val DIAGNOSTIC_MODE = 5L

    override fun part2() = computer.run {
        reset()
        input(DIAGNOSTIC_MODE)
        nextOutput() ?: error("No diagnostic code")
    }

    private val computer = IntCodeComputerFactory.buildIOComputer(input().text())

}

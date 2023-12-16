package fr.lidonis.adventofcode.y2019.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2019(DAY) {

    private const val DIAGNOSTIC_MODE = 5L

    private val computer = IntCodeComputerFactory.buildIOComputer(input().text())

    @Answer("13547311")
    override fun part1(): Long = computer.run {
        reset()
        input(1)
        run()
        val diagnosticCode = outputs.pollLast()
        check(outputs.all { it == 0L })
        diagnosticCode
    }

    @Answer("236453")
    override fun part2() = computer.run {
        reset()
        input(DIAGNOSTIC_MODE)
        nextOutput() ?: error("No diagnostic code")
    }
}

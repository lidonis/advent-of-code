package fr.lidonis.adventofcode.y2019.day9

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 9

@Suppress("unused")
object Day9 : AdventOfCode2019(DAY) {

    private val input = input().text()

    @Answer("3601950151")
    override fun part1() =
        IntCodeComputerFactory.buildIOComputer(input).compute(1)
            ?: error("No boost code")

    @Answer("64236")
    override fun part2() =
        IntCodeComputerFactory.buildIOComputer(input).compute(2)
            ?: error("No distress signal")

    private fun IntCodeComputer.compute(input: Long) = run {
        reset()
        input(input)
        nextOutput()
    }
}

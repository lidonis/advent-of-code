package fr.lidonis.adventofcode.y2019.day17

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 17

object Day17 : AdventOfCode2019(DAY) {

    override fun part1() = ascii.sumOfTheAlignmentParameters()

    override fun part2() = ascii.amountOfDustCollected() ?: error("No dust collected")

    private val ascii = AftScaffoldingControlAndInformationInterface(input().text())
}

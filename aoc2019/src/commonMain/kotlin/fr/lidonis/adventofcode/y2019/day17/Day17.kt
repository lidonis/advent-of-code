package fr.lidonis.adventofcode.y2019.day17

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 17

@Suppress("unused")
object Day17 : AdventOfCode2019(DAY) {

    private val ascii = AftScaffoldingControlAndInformationInterface(input().readText())

    @Answer("5788")
    override fun part1() = ascii.sumOfTheAlignmentParameters()

    @Answer("648545")
    override fun part2() = ascii.amountOfDustCollected() ?: error("No dust collected")
}

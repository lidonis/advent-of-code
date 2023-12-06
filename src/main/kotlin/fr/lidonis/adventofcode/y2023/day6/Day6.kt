package fr.lidonis.adventofcode.y2023.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 6

@Suppress("unused")
object Day6 : AdventOfCode2023(DAY) {

    private val waitForIt = WaitForIt(input().lines())

    @Answer("771628")
    override fun part1() = waitForIt.part1()

    @Answer("27363861")
    override fun part2() = waitForIt.part2()
}

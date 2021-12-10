package fr.lidonis.adventofcode.y2021.day9

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 9

object Day9 : AdventOfCode2021(DAY) {

    private val smokeBasin = SmokeBasin(input().lines())

    @Answer("468")
    override fun part1() = smokeBasin.sumRiskLowestPoints()

    @Answer("1280496")
    override fun part2() = smokeBasin.findThreeLargestBasinsSize().reduce(Int::times)
}

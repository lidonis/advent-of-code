package fr.lidonis.adventofcode.y2021.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2021(DAY) {

    private val submarineReport = SubmarineReport(input().lines())

    @Answer("845186")
    override fun part1() = submarineReport.powerConsumption()

    @Answer("4636702")
    override fun part2() = submarineReport.lifeSupportRating()

}

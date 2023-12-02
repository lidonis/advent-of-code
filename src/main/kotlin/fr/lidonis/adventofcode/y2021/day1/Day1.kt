package fr.lidonis.adventofcode.y2021.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 1

@Suppress("unused")
object Day1 : AdventOfCode2021(DAY) {

    private val sweepReport: SweepReport = SweepReport(input().lines().map(String::toInt))

    @Answer("1393")
    override fun part1() = sweepReport.countIncrease()

    @Answer("1359")
    override fun part2() = sweepReport.countWindowIncrease()
}

package fr.lidonis.adventofcode.y2020.day1

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 1

object Day1 : AdventOfCode2020(DAY) {

    private const val SUM = 2020
    private const val PART_1_SIZE = 2
    private const val PART_2_SIZE = 3

    override fun part1() = report(PART_1_SIZE)

    override fun part2() = report(PART_2_SIZE)

    private fun report(size: Int) =
        expenseReport.findCombinationBySum(size, SUM)?.reduce(Int::times)
            ?: error("No combination found")

    private val expenseReport = ExpenseReport(input().lines().map(String::toInt))
}

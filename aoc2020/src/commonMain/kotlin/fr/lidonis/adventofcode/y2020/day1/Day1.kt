package fr.lidonis.adventofcode.y2020.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.common.multiply
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 1

@Suppress("unused")
object Day1 : AdventOfCode2020(DAY) {

    private const val SUM = 2020
    private const val PART_1_SIZE = 2
    private const val PART_2_SIZE = 3

    private val expenseReport = ExpenseReport(input().readLines().map(String::toInt))

    @Answer("703131")
    override fun part1() = report(PART_1_SIZE)

    @Answer("272423970")
    override fun part2() = report(PART_2_SIZE)

    private fun report(size: Int) =
        expenseReport.findCombinationBySum(size, SUM)?.multiply()
            ?: error("No combination found")
}

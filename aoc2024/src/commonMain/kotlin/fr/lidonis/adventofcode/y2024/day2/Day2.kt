package fr.lidonis.adventofcode.y2024.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 2

@Suppress("unused")
object Day2 : AdventOfCode2024(DAY) {

    private val redNosedReports = RedNosedReports(input().lines())

    @Answer("")
    override fun part1() = redNosedReports.part1()

    @Answer("")
    override fun part2() = redNosedReports.part2()
}

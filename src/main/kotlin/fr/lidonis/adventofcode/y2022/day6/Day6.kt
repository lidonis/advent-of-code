package fr.lidonis.adventofcode.y2022.day6

import fr.lidonis.adventofcode.y2022.AdventOfCode2022


private const val DAY = 6

private const val PART1_SIZE = 4
private const val PART2_SIZE = 14

object Day6: AdventOfCode2022(DAY) {

    private val tuningTrouble = TuningTrouble(input().text())

    override fun part1() = tuningTrouble.firstChar(PART1_SIZE)

    override fun part2() = tuningTrouble.firstChar(PART2_SIZE)
}

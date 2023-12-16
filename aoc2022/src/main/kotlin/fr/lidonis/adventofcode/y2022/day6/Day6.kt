package fr.lidonis.adventofcode.y2022.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 6

private const val PART1_SIZE = 4
private const val PART2_SIZE = 14

@Suppress("unused")
object Day6 : AdventOfCode2022(DAY) {

    private val tuningTrouble = TuningTrouble(input().text())

    @Answer("1896")
    override fun part1() = tuningTrouble.startOfMessage(PART1_SIZE)

    @Answer("3452")
    override fun part2() = tuningTrouble.startOfMessage(PART2_SIZE)
}

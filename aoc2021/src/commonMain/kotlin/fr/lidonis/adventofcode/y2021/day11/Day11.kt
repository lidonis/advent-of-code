package fr.lidonis.adventofcode.y2021.day11

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 11

private const val TIMES_PART_1 = 100

@Suppress("unused")
object Day11 : AdventOfCode2021(DAY) {

    private val input = input().readLines()

    @Answer("1793")
    override fun part1() = DumboOctopus(input).countFlashes(TIMES_PART_1)

    @Answer("247")
    override fun part2() = DumboOctopus(input).stepToSynchronize()
}

package fr.lidonis.adventofcode.y2021.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 6

private const val NB_DAYS_PART_1 = 80
private const val NB_DAYS_PART_2 = 256

@Suppress("unused")
object Day6 : AdventOfCode2021(DAY) {

    private val lanternfish = Lanternfish(input().readText())

    @Answer("356190")
    override fun part1() = lanternfish.reproduce(NB_DAYS_PART_1)

    @Answer("1617359101538")
    override fun part2() = lanternfish.reproduce(NB_DAYS_PART_2)
}

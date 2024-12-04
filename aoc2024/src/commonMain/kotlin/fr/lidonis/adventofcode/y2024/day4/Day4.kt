package fr.lidonis.adventofcode.y2024.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2024(DAY) {

    private val ceresSearch = CeresSearch(input().lines())

    @Answer("2534")
    override fun part1() = ceresSearch.part1()

    @Answer("1866")
    override fun part2() = ceresSearch.part2()
}

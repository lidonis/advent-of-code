package fr.lidonis.adventofcode.y2024.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 6

@Suppress("unused")
object Day6 : AdventOfCode2024(DAY) {

    private val guardGallivant = GuardGallivant(input().lines())

    @Answer("5318")
    override fun part1() = guardGallivant.part1()

    @Answer("1831")
    override fun part2() = guardGallivant.part2()
}

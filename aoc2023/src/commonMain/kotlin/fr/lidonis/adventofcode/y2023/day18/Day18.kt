package fr.lidonis.adventofcode.y2023.day18

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 18

@Suppress("unused")
object Day18 : AdventOfCode2023(DAY) {

    private val lavaductLagoon = LavaductLagoon(input().lines())

    @Answer("76387")
    override fun part1() = lavaductLagoon.part1()

    @Answer("250022188522074")
    override fun part2() = lavaductLagoon.part2()
}

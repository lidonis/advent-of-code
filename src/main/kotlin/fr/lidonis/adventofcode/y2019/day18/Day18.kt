package fr.lidonis.adventofcode.y2019.day18

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 18

object Day18 : AdventOfCode2019(DAY) {

    @Answer("3216")
    override fun part1() = Vault(input().lines()).shortestPathStepCount() ?: error("Not path found")

    @Answer("1538")
    override fun part2() = ""
}

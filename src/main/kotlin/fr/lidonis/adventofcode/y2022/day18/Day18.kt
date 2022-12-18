package fr.lidonis.adventofcode.y2022.day18

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 18
object Day18 : AdventOfCode2022(DAY) {

    private val boilingBoulders = BoilingBoulders(input().lines())

    @Answer("3448")
    override fun part1() = boilingBoulders.surfaceArea()

    @Answer("2052")
    override fun part2() = boilingBoulders.exteriorSurfaceArea()
}

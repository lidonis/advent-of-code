package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 10

@Suppress("unused")
object Day10 : AdventOfCode2019(DAY) {

    private const val MULTIPLIER = 100
    private const val NTH_ASTEROID_TO_VAPORIZE = 200

    private val map = AsteroidMap(input())

    @Answer("274")
    override fun part1() = map.bestStation()?.second ?: error("No best station found")

    @Answer("305")
    override fun part2() = map.vaporize(NTH_ASTEROID_TO_VAPORIZE)
        ?.run { x * MULTIPLIER + y } ?: error("No asteroid to vaporize")
}

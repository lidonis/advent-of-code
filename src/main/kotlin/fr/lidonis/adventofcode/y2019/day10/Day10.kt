package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 10

object Day10 : AdventOfCode2019(DAY) {

    override fun part1() = map.bestStation()?.second ?: error("No best station found")

    private const val MULTIPLIER = 100
    private const val NTH_ASTEROID_TO_VAPORIZE = 200

    override fun part2() = map.vaporize(NTH_ASTEROID_TO_VAPORIZE)
        ?.run { x * MULTIPLIER + y } ?: error("No asteroid to vaporize")

    private val map = AsteroidMap(input().text())
}

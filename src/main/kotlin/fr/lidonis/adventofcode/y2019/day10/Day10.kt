package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day10.part1()} asteroids can be detected from the best location")
    println("The 200th asteroid to be vaporized is at ${Day10.part2()}")
}

private const val DAY = 10

object Day10 : AdventOfCode2019(DAY) {

    override fun part1() = map.bestStation()?.second ?: error("No best station found")

    private const val MULTIPLIER = 100
    private const val NTH_ASTEROID_TO_VAPORIZE = 200

    override fun part2() = map.vaporize(NTH_ASTEROID_TO_VAPORIZE)
        ?.run { x * MULTIPLIER + y } ?: error("No asteroid to vaporize")

    private val map = AsteroidMap(input().text())
}

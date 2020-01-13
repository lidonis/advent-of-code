package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day10.part1()} asteroids can be detected from the best location")
    println("The 200th asteroid to be vaporized is at ${Day10.part2()}")
}

object Day10 : AdventOfCode2019(10) {

    override fun part1() = map.bestStation()?.second ?: error("No best station found")
    override fun part2() = map.vaporize(200)?.run { x * 100 + y } ?: error("No asteroid to vaporize")

    private val map = AsteroidMap(input().text())
}

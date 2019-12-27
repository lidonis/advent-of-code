package fr.lidonis.adventofcode.y2019.day10

import InputReader

fun main() {
    println("${Day10.part1()} asteroids can be detected from the best location")
    println("The 200th asteroid to be vaporized is at ${Day10.part2()}")
}

object Day10 {

    fun part1() = map().bestStation()?.second

    fun part2() = map().vaporize(200).run { x * 100 + y }

    private fun map() = AsteroidMap(InputReader("day10.txt").text)
}
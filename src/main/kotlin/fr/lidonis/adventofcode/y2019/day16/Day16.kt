package fr.lidonis.adventofcode.y2019.day16

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The first eight digits in the final output list are ${Day16.part1()}")
    println("The eight-digit message embedded in the final output list are ${Day16.part2()}")
}

private const val DAY = 16

object Day16 : AdventOfCode2019(DAY) {

    private const val TIMES = 100

    override fun part1() = fft.phases(TIMES)
    override fun part2() = fft.embedded(TIMES)

    private const val RADIX = 10

    private val fft =
        FlawedFrequencyTransmission(input().text().map {
            Character.digit(it, RADIX)
        })
}

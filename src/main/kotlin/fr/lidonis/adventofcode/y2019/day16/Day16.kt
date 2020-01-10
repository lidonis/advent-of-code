package fr.lidonis.adventofcode.y2019.day16

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The first eight digits in the final output list are ${Day16.part1()}")
    println("The eight-digit message embedded in the final output list are ${Day16.part2()}")
}

object Day16 : AdventOfCode2019(16) {

    override fun part1() = fft.phases(100)
    override fun part2() = fft.embedded(100)

    private val fft =
        FlawedFrequencyTransmission(input().text().map {
            Character.digit(it, 10)
        })
}

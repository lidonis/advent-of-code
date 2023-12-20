package fr.lidonis.adventofcode.y2019.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 1

@Suppress("unused")
object Day1 : AdventOfCode2019(DAY) {

    private val spacecraft = Spacecraft(input().lines().map(String::toInt))

    @Answer("3305301")
    override fun part1(): Int = spacecraft.fuelRequirements()

    @Answer("4955106")
    override fun part2(): Int = spacecraft.fullFuelRequirements()
}

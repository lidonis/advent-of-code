package fr.lidonis.adventofcode.y2019.day1

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 1

object Day1 : AdventOfCode2019(DAY) {

    override fun part1(): Int = spacecraft.fuelRequirements()
    override fun part2(): Int = spacecraft.fullFuelRequirements()

    private val spacecraft = Spacecraft(input().text().lines().map(String::toInt))
}

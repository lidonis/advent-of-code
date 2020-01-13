package fr.lidonis.adventofcode.y2019.day1

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The sum of the fuel requirements for all of the modules on the spacecraft is ${Day1.part1()}")
    println(
        "the sum of the fuel requirements" +
                " for all of the modules on your spacecraft when also taking into account the mass of the added fuel" +
                " is ${Day1.part2()}"
    )
}

private const val DAY = 1

object Day1 : AdventOfCode2019(DAY) {

    override fun part1(): Int = spacecraft.fuelRequirements()
    override fun part2(): Int = spacecraft.fullFuelRequirements()

    private val spacecraft = Spacecraft(input().lines().map(String::toInt))
}

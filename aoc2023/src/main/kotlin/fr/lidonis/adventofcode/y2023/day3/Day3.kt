package fr.lidonis.adventofcode.y2023.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2023(DAY) {

    private val gearRatios = GearRatios(input().lines())

    @Answer("527369")
    override fun part1() = gearRatios.part1()

    @Answer("73074886")
    override fun part2() = gearRatios.part2()
}

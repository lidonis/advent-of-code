package fr.lidonis.adventofcode.y2021.day7

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 7


object Day7 : AdventOfCode2021(DAY) {

    private val crabSubmarines = CrabSubmarines(input().text())

    @Answer("336701")
    override fun part1() = crabSubmarines.fuelToAlignConstantBurn()

    @Answer("95167302")
    override fun part2() = crabSubmarines.fuelToAlignIncreasingBurn()
}

package fr.lidonis.adventofcode.y2020.day5

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 5

object Day5 : AdventOfCode2020(DAY) {

    override fun part1() = binaryBoardingScanner.highestSeatID()

    override fun part2() = binaryBoardingScanner.findMissingSeatId()

    private val binaryBoardingScanner = BinaryBoardingScanner(input().lines())

}

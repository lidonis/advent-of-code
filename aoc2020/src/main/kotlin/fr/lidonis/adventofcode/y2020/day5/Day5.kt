package fr.lidonis.adventofcode.y2020.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2020(DAY) {

    private val binaryBoardingScanner = BinaryBoardingScanner(input().lines())

    @Answer("813")
    override fun part1() = binaryBoardingScanner.highestSeatID()

    @Answer("612")
    override fun part2() = binaryBoardingScanner.findMissingSeatId()
}

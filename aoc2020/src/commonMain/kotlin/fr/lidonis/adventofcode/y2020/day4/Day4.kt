package fr.lidonis.adventofcode.y2020.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2020(DAY) {

    private val passportScanner = PassportScanner(input())

    @Answer("208")
    override fun part1() = passportScanner.countValidRequiredField()

    @Answer("167")
    override fun part2() = passportScanner.countValidValues()
}

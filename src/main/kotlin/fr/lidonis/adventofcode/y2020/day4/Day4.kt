package fr.lidonis.adventofcode.y2020.day4

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 4

object Day4 : AdventOfCode2020(DAY) {

    private val passportScanner = PassportScanner(input().text())

    override fun part1() = passportScanner.countValidRequiredField()

    override fun part2() = passportScanner.countValidValues()
}

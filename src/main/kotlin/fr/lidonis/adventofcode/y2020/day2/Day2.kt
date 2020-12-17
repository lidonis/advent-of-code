package fr.lidonis.adventofcode.y2020.day2

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 2

object Day2 : AdventOfCode2020(DAY) {

    private val passwordDatabase = PasswordDatabase(input().lines())

    override fun part1() = passwordDatabase.countValidPasswordSledRentalPolicy()

    override fun part2() = passwordDatabase.countValidPasswordTobogganCorporatePolicy()
}

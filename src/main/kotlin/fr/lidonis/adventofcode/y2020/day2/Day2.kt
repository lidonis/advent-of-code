package fr.lidonis.adventofcode.y2020.day2

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 2

object Day2 : AdventOfCode2020(DAY) {

    override fun part1() = passwordDatabase.countValidPasswordSledRentalPolicy()

    override fun part2() = passwordDatabase.countValidPasswordTobogganCorporatePolicy()

    private val passwordDatabase = PasswordDatabase(input().lines())
}

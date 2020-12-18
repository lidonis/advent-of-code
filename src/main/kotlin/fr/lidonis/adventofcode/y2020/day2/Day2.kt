package fr.lidonis.adventofcode.y2020.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 2

object Day2 : AdventOfCode2020(DAY) {

    private val passwordDatabase = PasswordDatabase(input().lines())

    @Answer("528")
    override fun part1() = passwordDatabase.countValidPasswordSledRentalPolicy()

    @Answer("497")
    override fun part2() = passwordDatabase.countValidPasswordTobogganCorporatePolicy()
}

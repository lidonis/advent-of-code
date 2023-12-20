package fr.lidonis.adventofcode.y2022.day11

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 11

@Suppress("unused")
object Day11 : AdventOfCode2022(DAY) {

    @Answer("50830")
    override fun part1() = MonkeyInTheMiddle(input().readLines()).monkeyBusiness()

    @Answer("14399640002")
    override fun part2() = MonkeyInTheMiddle(input().readLines()).monkeyBusinessNoWorries()
}

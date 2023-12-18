package fr.lidonis.adventofcode.y2022.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 2

@Suppress("unused")
object Day2 : AdventOfCode2022(DAY) {

    @Answer("11841")
    override fun part1() = RockPaperScissors(input().lines()).score()

    @Answer("13022")
    override fun part2() = RockPaperScissors(input().lines()).scoreAgain()
}

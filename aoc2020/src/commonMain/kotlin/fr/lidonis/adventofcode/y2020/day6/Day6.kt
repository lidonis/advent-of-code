package fr.lidonis.adventofcode.y2020.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 6

@Suppress("unused")
object Day6 : AdventOfCode2020(DAY) {

    private val customsForms = CustomsForms(input().readText())

    @Answer("6885")
    override fun part1() = customsForms.sumOfYesAnyone()

    @Answer("3550")
    override fun part2() = customsForms.sumOfYesEveryone()
}

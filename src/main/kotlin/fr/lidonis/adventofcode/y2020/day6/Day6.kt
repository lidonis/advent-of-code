package fr.lidonis.adventofcode.y2020.day6

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 6

object Day6 : AdventOfCode2020(DAY) {

    private val customsForms = CustomsForms(input().text())

    override fun part1() = customsForms.sumOfYesAnyone()

    override fun part2() = customsForms.sumOfYesEveryone()
}

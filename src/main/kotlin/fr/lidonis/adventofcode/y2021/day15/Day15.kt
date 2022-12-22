package fr.lidonis.adventofcode.y2021.day15

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 15

object Day15 : AdventOfCode2021(DAY) {

    private val chiton = Chiton(input().lines())

    @Answer("2621")
    override fun part1() = chiton.lowestRiskPath()

    @Answer("2843834241366")
    override fun part2() = Unit
}

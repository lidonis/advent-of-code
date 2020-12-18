package fr.lidonis.adventofcode.y2020.day11

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 11

object Day11 : AdventOfCode2020(DAY) {

    @Answer("2238")
    override fun part1() = AdjacentSeatingSystem(input().lines()).stabilize()

    @Answer("2013")
    override fun part2() = FirstSeatingSystem(input().lines()).stabilize()
}

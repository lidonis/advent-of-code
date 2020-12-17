package fr.lidonis.adventofcode.y2020.day11

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 11

object Day11 : AdventOfCode2020(DAY) {

    override fun part1() = AdjacentSeatingSystem(input().lines()).stabilize()

    override fun part2() = FirstSeatingSystem(input().lines()).stabilize()
}

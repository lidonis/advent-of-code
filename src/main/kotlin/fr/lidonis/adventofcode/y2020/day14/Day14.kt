package fr.lidonis.adventofcode.y2020.day14

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 14

object Day14 : AdventOfCode2020(DAY) {

    override fun part1() = DockingDataV1(input().lines()).runProgram()

    override fun part2() = DockingDataV2(input().lines()).runProgram()

}

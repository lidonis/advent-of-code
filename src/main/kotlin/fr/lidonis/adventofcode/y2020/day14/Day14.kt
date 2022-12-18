package fr.lidonis.adventofcode.y2020.day14

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 14

@Suppress("unused")
object Day14 : AdventOfCode2020(DAY) {

    @Answer("9879607673316")
    override fun part1() = DockingDataV1(input().lines()).runProgram()

    @Answer("3435342392262")
    override fun part2() = DockingDataV2(input().lines()).runProgram()
}

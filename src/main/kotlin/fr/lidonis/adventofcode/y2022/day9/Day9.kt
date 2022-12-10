package fr.lidonis.adventofcode.y2022.day9

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 9

private const val NB_TAILS_PART1 = 1
private const val NB_TAILS_PART2 = 9

object Day9: AdventOfCode2022(DAY) {

    private val ropeBridge = RopeBridge(input().lines())

    @Answer("5902")
    override fun part1() = ropeBridge.move(NB_TAILS_PART1).size
    @Answer("2445")
    override fun part2() = ropeBridge.move(NB_TAILS_PART2).size
}

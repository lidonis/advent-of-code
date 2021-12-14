package fr.lidonis.adventofcode.y2021.day14

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 14

private const val STEPS_PART_1 = 10
private const val STEPS_PART_2 = 40

object Day14 : AdventOfCode2021(DAY) {

    private val extendedPolymerization = ExtendedPolymerization(input().lines())

    @Answer("2621")
    override fun part1() = extendedPolymerization.mostAndLeastCommonElements(STEPS_PART_1)

    @Answer("2843834241366")
    override fun part2() = extendedPolymerization.mostAndLeastCommonElements(STEPS_PART_2)
}

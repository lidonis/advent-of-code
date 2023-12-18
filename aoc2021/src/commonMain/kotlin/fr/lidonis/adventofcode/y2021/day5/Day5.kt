package fr.lidonis.adventofcode.y2021.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2021(DAY) {

    private val hydrothermalVents = HydrothermalVents(input().lines())

    @Answer("5280")
    override fun part1() = hydrothermalVents.countAtLeastTwoLinesOverlapWithHorizontalOrVertical()

    @Answer("16716")
    override fun part2() = hydrothermalVents.countAtLeastTwoLinesOverlap()
}

package fr.lidonis.adventofcode.y2022.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2022(DAY) {

    private val campCleanup = CampCleanup(input().lines())

    @Answer("569")
    override fun part1() = campCleanup.contains()

    @Answer("936")
    override fun part2() = campCleanup.overlap()

}

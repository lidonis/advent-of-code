package fr.lidonis.adventofcode.y2023.day15

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 15

@Suppress("unused")
object Day15 : AdventOfCode2023(DAY) {

    private val lensLibrary = LensLibrary(input().readText())

    @Answer("503487")
    override fun part1() = lensLibrary.part1()

    @Answer("261505")
    override fun part2() = lensLibrary.part2()
}

package fr.lidonis.adventofcode.y2023.day17

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 17

@Suppress("unused")
object Day17 : AdventOfCode2023(DAY) {

    private val clumsyCrucible = ClumsyCrucible(input().readLines())

    @Answer("942")
    override fun part1() = clumsyCrucible.part1()

    @Answer("1082")
    override fun part2() = clumsyCrucible.part2()
}

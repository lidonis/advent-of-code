package fr.lidonis.adventofcode.y2023.day11

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 11

@Suppress("unused")
object Day11 : AdventOfCode2023(DAY) {

    private val cosmicExpansion = CosmicExpansion(input().lines())

    @Answer("9918828")
    override fun part1() = cosmicExpansion.part1()

    @Answer("692506533832")
    override fun part2() = cosmicExpansion.part2()
}

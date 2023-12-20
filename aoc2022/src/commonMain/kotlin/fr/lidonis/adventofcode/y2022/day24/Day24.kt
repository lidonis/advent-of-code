package fr.lidonis.adventofcode.y2022.day24

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 24

@Suppress("unused")
object Day24 : AdventOfCode2022(DAY) {

    private val blizzardBasin = BlizzardBasin(input().readText())

    @Answer("245")
    override fun part1() = blizzardBasin.go

    @Answer("798")
    override fun part2() = blizzardBasin.shortestPathBackAndForth()
}

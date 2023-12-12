package fr.lidonis.adventofcode.y2023.day12

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 12

@Suppress("unused")
object Day12 : AdventOfCode2023(DAY) {

    private val hotSprings = HotSprings(input().lines())

    @Answer("7490")
    override fun part1() = hotSprings.part1()

    @Answer("65607131946466")
    override fun part2() = hotSprings.part2()
}

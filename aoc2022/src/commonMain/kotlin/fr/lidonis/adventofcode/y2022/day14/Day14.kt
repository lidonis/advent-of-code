package fr.lidonis.adventofcode.y2022.day14

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 14

@Suppress("unused")
object Day14 : AdventOfCode2022(DAY) {

    private val distressSignal = RegolithReservoir(input().lines())

    @Answer("763")
    override fun part1() = distressSignal.countSandBeforeAbyss()

    @Answer("23921")
    override fun part2() = distressSignal.totalSand()
}

package fr.lidonis.adventofcode.y2022.day10

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 10

@Suppress("unused")
object Day10: AdventOfCode2022(DAY) {

    private val cathodeRayTube = CathodeRayTube(input().lines())

    @Answer("14420")
    override fun part1() = cathodeRayTube.signalStrengths().sum()

    @Answer("RGLRBZAU")
    override fun part2() = cathodeRayTube.ocr()
}

package fr.lidonis.adventofcode.y2022.day13

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 13

@Suppress("unused")
object Day13 : AdventOfCode2022(DAY) {

    private val distressSignal = DistressSignal(input().lines())

    @Answer("5675")
    override fun part1() = distressSignal.countOrdered()

    @Answer("20383")
    override fun part2() = distressSignal.decoderKey()
}

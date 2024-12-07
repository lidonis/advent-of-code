package fr.lidonis.adventofcode.y2024.day7

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 7

@Suppress("unused")
object Day7 : AdventOfCode2024(DAY) {

    private val bridgeRepair = BridgeRepair(input().lines())

    @Answer("882304362421")
    override fun part1() = bridgeRepair.part1()

    @Answer("145149066755184")
    override fun part2() = bridgeRepair.part2()
}

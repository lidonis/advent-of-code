package fr.lidonis.adventofcode.y2022.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2022(DAY) {

    private val supplyStacks = SupplyStacks(input().lines())

    @Answer("SBPQRSCDF")
    override fun part1() = supplyStacks.move()

    @Answer("RGLVRCQSB")
    override fun part2() = supplyStacks.moveBulk()

}

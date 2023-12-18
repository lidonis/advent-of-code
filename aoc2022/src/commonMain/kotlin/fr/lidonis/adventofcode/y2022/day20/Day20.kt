package fr.lidonis.adventofcode.y2022.day20

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 20

@Suppress("unused")
object Day20 : AdventOfCode2022(DAY) {

    private val grovePositioningSystem = GrovePositioningSystem(input().lines())

    @Answer("19559")
    override fun part1() = grovePositioningSystem.sum()

    @Answer("912226207972")
    override fun part2() = grovePositioningSystem.sumDecrypted()
}

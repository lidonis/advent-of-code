package fr.lidonis.adventofcode.y2023.day21

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 21

private const val i = 64

@Suppress("unused")
object Day21 : AdventOfCode2023(DAY) {

    private val stepCounter = StepCounter(input().lines())

    @Answer("")
    override fun part1() = stepCounter.part1(64)

    @Answer("")
    override fun part2() = stepCounter.part2()
}

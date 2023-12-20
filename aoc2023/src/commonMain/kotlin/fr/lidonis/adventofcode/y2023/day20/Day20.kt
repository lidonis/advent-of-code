package fr.lidonis.adventofcode.y2023.day20

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 20

@Suppress("unused")
object Day20 : AdventOfCode2023(DAY) {

    private val pulsePropagation = PulsePropagation(input().lines())

    @Answer("886701120")
    override fun part1() = pulsePropagation.part1()

    @Answer("228134431501037")
    override fun part2() = pulsePropagation.part2()
}

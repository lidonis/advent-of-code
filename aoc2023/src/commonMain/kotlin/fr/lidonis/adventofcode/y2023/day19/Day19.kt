package fr.lidonis.adventofcode.y2023.day19

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 19

@Suppress("unused")
object Day19 : AdventOfCode2023(DAY) {

    private val aplenty = Aplenty(input().readText())

    @Answer("575412")
    override fun part1() = aplenty.part1()

    @Answer("126107942006821")
    override fun part2() = aplenty.part2()
}

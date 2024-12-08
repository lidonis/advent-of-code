package fr.lidonis.adventofcode.y2024.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 8

@Suppress("unused")
object Day8 : AdventOfCode2024(DAY) {

    private val resonantCollinearity = ResonantCollinearity(input().lines())

    @Answer("344")
    override fun part1() = resonantCollinearity.part1()

    @Answer("1182")
    override fun part2() = resonantCollinearity.part2()
}

package fr.lidonis.adventofcode.y2024.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2024(DAY) {

    private val mullItOver = MullItOver(input())

    @Answer("183380722")
    override fun part1() = mullItOver.part1()

    @Answer("82733683")
    override fun part2() = mullItOver.part2()
}

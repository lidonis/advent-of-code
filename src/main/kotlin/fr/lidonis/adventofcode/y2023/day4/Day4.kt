package fr.lidonis.adventofcode.y2023.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2023(DAY) {

    private val scratchcards = Scratchcards(input().lines())

    @Answer("25571")
    override fun part1() = scratchcards.part1()

    @Answer("8805731")
    override fun part2() = scratchcards.part2()
}

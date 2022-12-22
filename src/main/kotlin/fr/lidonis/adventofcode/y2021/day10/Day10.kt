package fr.lidonis.adventofcode.y2021.day10

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 10

object Day10 : AdventOfCode2021(DAY) {

    private val syntaxScoring = SyntaxScoring(input().lines())

    @Answer("193275")
    override fun part1() = syntaxScoring.scoreCorruptedLines()

    @Answer("2429644557")
    override fun part2() = syntaxScoring.scoreIncompleteLines()
}

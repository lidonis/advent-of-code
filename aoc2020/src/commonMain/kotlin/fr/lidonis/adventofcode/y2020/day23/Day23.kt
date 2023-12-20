package fr.lidonis.adventofcode.y2020.day23

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 23

private const val NB_FIRST_MOVES = 100
private const val NB_SECOND_MOVES = 10_000_000
private const val SECOND_SIZE = 1_000_000

@Suppress("unused")
object Day23 : AdventOfCode2020(DAY) {

    @Answer("43769582")
    override fun part1(): String {
        val crabCups = CrabCups(input().readText())
        crabCups.play(NB_FIRST_MOVES)
        return crabCups.order
    }

    @Answer("264692662390")
    override fun part2(): Long {
        val crabCups = CrabCups(input().readText(), SECOND_SIZE)
        crabCups.play(NB_SECOND_MOVES)
        val firstStar = crabCups.cupNextTo(1)
        val secondStar = crabCups.cupNextTo(firstStar)
        return firstStar.toLong() * secondStar.toLong()
    }
}

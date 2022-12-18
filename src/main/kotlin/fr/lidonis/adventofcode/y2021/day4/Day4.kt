package fr.lidonis.adventofcode.y2021.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2021(DAY) {

    private val bingo = Bingo(input().lines())

    @Answer("71708")
    override fun part1() = bingo.finalScoreFirstWin()

    @Answer("34726")
    override fun part2() = bingo.finalScoreLastWin()

}

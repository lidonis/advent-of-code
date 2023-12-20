package fr.lidonis.adventofcode.y2020.day15

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 15
private const val TURN_PART_1 = 2020
private const val TURN_PART_2 = 30000000

@Suppress("unused")
object Day15 : AdventOfCode2020(DAY) {

    private val text = input().readText()

    @Answer("517")
    override fun part1() = RambunctiousRecitation(text).lastSpoken(TURN_PART_1)

    @Answer("1047739")
    override fun part2() = RambunctiousRecitation(text).lastSpoken(TURN_PART_2)
}

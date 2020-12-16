package fr.lidonis.adventofcode.y2020.day15

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 15
private const val TURN_PART_1 = 2020
private const val TURN_PART_2 = 30000000

object Day15 : AdventOfCode2020(DAY) {

    override fun part1() = rambunctiousRecitation.lastSpoken(TURN_PART_1)

    override fun part2() = rambunctiousRecitation.lastSpoken(TURN_PART_2)

    private val rambunctiousRecitation = RambunctiousRecitation(input().text())

}

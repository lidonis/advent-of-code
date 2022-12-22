package fr.lidonis.adventofcode.y2021.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 8


object Day8 : AdventOfCode2021(DAY) {

    private val sevenSegmentSearch = SevenSegmentSearch(input().lines())

    @Answer("336701")
    override fun part1() = sevenSegmentSearch.countUniqueSegmentNumber()

    @Answer("95167302")
    override fun part2() = sevenSegmentSearch.sumValues()
}

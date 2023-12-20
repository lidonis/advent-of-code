package fr.lidonis.adventofcode.y2023.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 1

@Suppress("unused")
object Day1 : AdventOfCode2023(DAY) {

    private val trebuchet = Trebuchet(input().readLines())

    @Answer("54597")
    override fun part1() = trebuchet.part1()

    @Answer("54504")
    override fun part2() = trebuchet.part2()
}

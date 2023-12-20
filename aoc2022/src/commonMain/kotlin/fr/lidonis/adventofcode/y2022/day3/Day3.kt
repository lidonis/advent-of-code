package fr.lidonis.adventofcode.y2022.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2022(DAY) {

    private val rucksackReorganization = RucksackReorganization(input().readLines())

    @Answer("7811")
    override fun part1() = rucksackReorganization.rucksack()

    @Answer("2639")
    override fun part2() = rucksackReorganization.elves()
}

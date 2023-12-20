package fr.lidonis.adventofcode.y2020.day10

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 10

@Suppress("unused")
object Day10 : AdventOfCode2020(DAY) {

    private val adapterArray = AdapterArray(input().readLines().map(String::toInt))

    @Answer("1920")
    override fun part1() = adapterArray.differences()

    @Answer("1511207993344")
    override fun part2() = adapterArray.arrangements()
}

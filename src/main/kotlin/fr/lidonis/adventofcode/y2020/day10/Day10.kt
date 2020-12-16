package fr.lidonis.adventofcode.y2020.day10

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 10

object Day10 : AdventOfCode2020(DAY) {

    override fun part1() = adapterArray.differences()
    override fun part2() = adapterArray.arrangements()

    private val adapterArray = AdapterArray(input().lines().map(String::toInt))

}

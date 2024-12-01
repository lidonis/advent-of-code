package fr.lidonis.adventofcode.y2024.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 1

@Suppress("unused")
object Day1 : AdventOfCode2024(DAY) {

    private val historianHysteria = HistorianHysteria(input().lines())

    @Answer("1223326")
    override fun part1() = historianHysteria.part1()

    @Answer("21070419")
    override fun part2() = historianHysteria.part2()
}

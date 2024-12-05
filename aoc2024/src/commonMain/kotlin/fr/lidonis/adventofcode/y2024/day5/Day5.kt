package fr.lidonis.adventofcode.y2024.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2024.AdventOfCode2024

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2024(DAY) {

    private val printQueue = PrintQueue(input().lines())

    @Answer("5762")
    override fun part1() = printQueue.part1()

    @Answer("4130")
    override fun part2() = printQueue.part2()
}

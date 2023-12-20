package fr.lidonis.adventofcode.y2023.day10

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 10

@Suppress("unused")
object Day10 : AdventOfCode2023(DAY) {

    private val pipeMaze = PipeMaze(input().lines())

    @Answer("6757")
    override fun part1() = pipeMaze.part1()

    @Answer("523")
    override fun part2() = pipeMaze.part2()
}

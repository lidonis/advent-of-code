package fr.lidonis.adventofcode.y2021.day15

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 15

@Suppress("unused")
object Day15 : AdventOfCode2021(DAY) {

    private val chiton = Chiton(input().lines())

    @Answer("748")
    override fun part1() = chiton.part1()

    @Answer("3045")
    override fun part2() = chiton.part2()
}

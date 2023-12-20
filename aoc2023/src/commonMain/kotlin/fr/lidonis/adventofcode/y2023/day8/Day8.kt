package fr.lidonis.adventofcode.y2023.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 8

@Suppress("unused")
object Day8 : AdventOfCode2023(DAY) {

    private val hauntedWasteland = HauntedWasteland(input().lines())

    @Answer("17621")
    override fun part1() = hauntedWasteland.part1()

    @Answer("20685524831999")
    override fun part2() = hauntedWasteland.part2()
}

package fr.lidonis.adventofcode.y2022.day25

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 25

private const val PART_2 = "Start the Blender"

@Suppress("unused")
object Day25 : AdventOfCode2022(DAY) {

    private val fullOfHotAir = FullOfHotAir(input().readLines())

    @Answer("2-212-2---=00-1--102")
    override fun part1() = fullOfHotAir.sum()

    @Answer(PART_2)
    override fun part2() = PART_2
}

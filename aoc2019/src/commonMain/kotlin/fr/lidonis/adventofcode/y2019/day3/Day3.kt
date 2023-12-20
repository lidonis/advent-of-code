package fr.lidonis.adventofcode.y2019.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2019(DAY) {

    private val wires = input().readLines().map { line -> line.split(",") }.run {
        CrossedWires(this[0], this[1])
    }

    @Answer("2180")
    override fun part1() = wires.minimumDistance() ?: error("No intersection found")

    @Answer("112316")
    override fun part2() = wires.fewestSteps() ?: error("No intersection found")
}

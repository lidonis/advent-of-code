package fr.lidonis.adventofcode.y2019.day3

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 3

object Day3 : AdventOfCode2019(DAY) {

    override fun part1() = wires.minimumDistance() ?: error("No intersection found")
    override fun part2() = wires.fewestSteps() ?: error("No intersection found")

    private val wires = input().lines().map { line -> line.split(",") }.run {
        CrossedWires(this[0], this[1])
    }
}

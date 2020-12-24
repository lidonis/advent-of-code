package fr.lidonis.adventofcode

import fr.lidonis.adventofcode.common.InputReader

abstract class AdventOfCode(val year: Int) {

    abstract val day: Int
    internal fun input() = InputReader("/input/y$year/day$day.txt")

    abstract fun part1(): Any
    abstract fun part2(): Any
}

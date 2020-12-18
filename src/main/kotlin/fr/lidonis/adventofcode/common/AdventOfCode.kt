package fr.lidonis.adventofcode.common

abstract class AdventOfCode(val year: Int) {

    internal abstract val day: Int
    internal fun input() = InputReader("/input/y$year/day$day.txt")

    abstract fun part1(): Any
    abstract fun part2(): Any
}

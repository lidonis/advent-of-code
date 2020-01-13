package fr.lidonis.adventofcode.common

import InputReader

abstract class AdventOfCode(private val year: Int) {

    internal abstract val day: Int
    internal fun input() = InputReader("day$day.txt")

    abstract fun part1(): Any
    abstract fun part2(): Any
}

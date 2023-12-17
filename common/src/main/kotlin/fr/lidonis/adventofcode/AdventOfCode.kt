package fr.lidonis.adventofcode

import fr.lidonis.adventofcode.common.ResourceReader

abstract class AdventOfCode(val year: Int) {

    abstract val day: Int
    protected fun input() =
        "/input/y$year/day$day.txt".let { inputPath ->
            ResourceReader(inputPath) ?: error("Input Resource not found: $inputPath")
        }

    abstract fun part1(): Any
    abstract fun part2(): Any
}

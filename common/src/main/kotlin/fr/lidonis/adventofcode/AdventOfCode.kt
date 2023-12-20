package fr.lidonis.adventofcode

import java.io.File

abstract class AdventOfCode(private val year: Int) {

    abstract val day: Int
    protected fun input() =
        File("../input/input/y$year/day$day/input.txt")
            .readText()
            .trimIndent()

    abstract fun part1(): Any
    abstract fun part2(): Any
}

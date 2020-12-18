package fr.lidonis.adventofcode.y2020.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 8

object Day8 : AdventOfCode2020(DAY) {

    @Answer("1384")
    override fun part1(): Int {
        try {
            HandheldGameConsole(input().lines()).run()
        } catch (e: HandheldGameConsole.InfiniteLoopException) {
            return e.value
        }
        return 0
    }

    @Answer("761")
    override fun part2() = CorruptionChecker(input().lines()).run()
        ?: error("No corruption found")
}

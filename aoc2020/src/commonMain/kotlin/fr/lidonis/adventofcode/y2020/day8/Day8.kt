package fr.lidonis.adventofcode.y2020.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020
import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.InfiniteLoopException

private const val DAY = 8

@Suppress("unused")
object Day8 : AdventOfCode2020(DAY) {

    @Answer("1384")
    override fun part1(): Int {
        HandheldGameConsole(input().readLines()).run().fold(
            onSuccess = { error("No infinite loop found") },
            onFailure = {
                return if (it is InfiniteLoopException) {
                    it.value
                } else {
                    throw it
                }
            }
        )
    }

    @Answer("761")
    override fun part2() = CorruptionChecker(input().readLines()).run()
        ?: error("No corruption found")
}

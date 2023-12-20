package fr.lidonis.adventofcode.y2020.day13

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 13

@Suppress("unused")
object Day13 : AdventOfCode2020(DAY) {

    private val shuttleSearch = ShuttleSearch(input().readLines())

    @Answer("2935")
    override fun part1() =
        shuttleSearch.searchBus()?.let { (busId, waitTime) -> busId * waitTime } ?: error("Bus not found")

    @Answer("836024966345345")
    override fun part2() = shuttleSearch.findTimestamp()
}

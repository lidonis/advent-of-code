package fr.lidonis.adventofcode.y2020.day13

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 13

object Day13 : AdventOfCode2020(DAY) {

    private val shuttleSearch = ShuttleSearch(input().lines())

    override fun part1() =
        shuttleSearch.searchBus()?.let { (busId, waitTime) -> busId * waitTime } ?: error("Bus not found")

    override fun part2() = shuttleSearch.findTimestamp()
}

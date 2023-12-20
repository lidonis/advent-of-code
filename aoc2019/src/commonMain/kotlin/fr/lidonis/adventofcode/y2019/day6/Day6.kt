package fr.lidonis.adventofcode.y2019.day6

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 6

@Suppress("unused")
object Day6 : AdventOfCode2019(DAY) {

    private val orbitMap = OrbitMap(input().readLines())

    @Answer("271151")
    override fun part1() = orbitMap.countTotalOrbits()

    @Answer("388")
    override fun part2() = orbitMap.minimumOrbitalTransfers("YOU", "SAN")
}

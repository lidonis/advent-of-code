package fr.lidonis.adventofcode.y2019.day6

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 6

object Day6 : AdventOfCode2019(DAY) {

    override fun part1() = orbitMap.countTotalOrbits()
    override fun part2() = orbitMap.minimumOrbitalTransfers("YOU", "SAN")

    private val orbitMap = OrbitMap(input().text())
}

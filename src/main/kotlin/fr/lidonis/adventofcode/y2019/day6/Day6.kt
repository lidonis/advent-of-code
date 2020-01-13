package fr.lidonis.adventofcode.y2019.day6

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The total number of direct and indirect orbits is ${Day6.part1()}")
    println("The minimum number of orbital transfers required is ${Day6.part2()}")
}

object Day6 : AdventOfCode2019(6) {

    override fun part1() = orbitMap.countTotalOrbits()
    override fun part2() = orbitMap.minimumOrbitalTransfers("YOU", "SAN")

    private val orbitMap = OrbitMap(input().text())
}

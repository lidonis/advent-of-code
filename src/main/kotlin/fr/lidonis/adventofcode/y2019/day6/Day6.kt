package fr.lidonis.adventofcode.y2019.day6

import InputReader


fun main() {
    println("The total number of direct and indirect orbits is ${Day6.part1()}")
    println("The minimum number of orbital transfers required is ${Day6.part2()}")
}

object Day6 {
    fun part1() = orbitMap().countTotalOrbits()

    fun part2() = orbitMap().minimumOrbitalTransfers("YOU", "SAN")

    private fun orbitMap() = OrbitMap(InputReader("day6.txt").text)
}
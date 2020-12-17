package fr.lidonis.adventofcode.y2019.day12

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 12

object Day12 : AdventOfCode2019(DAY) {

    private val galileanMoons = GalileanMoons(input().lines())

    override fun part1() = galileanMoons.totalEnergy()

    override fun part2() = galileanMoons.stepsToRepeatPreviousState()
}

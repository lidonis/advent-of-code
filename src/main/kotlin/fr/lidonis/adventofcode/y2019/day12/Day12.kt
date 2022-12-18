package fr.lidonis.adventofcode.y2019.day12

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 12

@Suppress("unused")
object Day12 : AdventOfCode2019(DAY) {

    private val galileanMoons = GalileanMoons(input().lines())

    @Answer("8310")
    override fun part1() = galileanMoons.totalEnergy()

    @Answer("319290382980408")
    override fun part2() = galileanMoons.stepsToRepeatPreviousState()
}

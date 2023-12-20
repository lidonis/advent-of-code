package fr.lidonis.adventofcode.y2020.day12

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 12

@Suppress("unused")
object Day12 : AdventOfCode2020(DAY) {

    private val navigationSystem = NavigationSystem(input().readLines())

    @Answer("441")
    override fun part1() = navigationSystem.distanceDirection()

    @Answer("40014")
    override fun part2() = navigationSystem.distanceWaypoint()
}

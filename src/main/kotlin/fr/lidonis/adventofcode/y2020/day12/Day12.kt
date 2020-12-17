package fr.lidonis.adventofcode.y2020.day12

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 12

object Day12 : AdventOfCode2020(DAY) {

    private val navigationSystem = NavigationSystem(input().lines())

    override fun part1() = navigationSystem.distanceDirection()

    override fun part2() = navigationSystem.distanceWaypoint()
}

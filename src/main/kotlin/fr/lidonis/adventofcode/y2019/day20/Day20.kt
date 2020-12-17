package fr.lidonis.adventofcode.y2019.day20

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 20

object Day20 : AdventOfCode2019(DAY) {

    private val maze = SpaceWarpingMaze(input().text())

    override fun part1() = maze.shortestPathDonut() ?: error("Can't find path")

    override fun part2() = maze.shortestPathInception() ?: error("Can't find path")
}

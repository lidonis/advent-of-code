package fr.lidonis.adventofcode.y2019.day20

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 20

@Suppress("unused")
object Day20 : AdventOfCode2019(DAY) {

    private val maze = SpaceWarpingMaze(input().text())

    @Answer("620")
    override fun part1() = maze.shortestPathDonut() ?: error("Can't find path")

    @Answer("7366")
    override fun part2() = maze.shortestPathInception() ?: error("Can't find path")
}

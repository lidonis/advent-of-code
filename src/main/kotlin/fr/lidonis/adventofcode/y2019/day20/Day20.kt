package fr.lidonis.adventofcode.y2019.day20

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("It takes ${Day20.part1()} to get from the open tile marked AA to the open tile marked ZZ")
    println(
        "it takes ${Day20.part2()} to get from the open tile marked AA to the open tile marked ZZ," +
                " both at the outermost layer"
    )
}

object Day20 : AdventOfCode2019(20) {
    override fun part1() = maze.shortestPathDonut() ?: error("Can't find path")
    override fun part2() = maze.shortestPathInception() ?: error("Can't find path")

    private val maze = SpaceWarpingMaze(input().text())
}

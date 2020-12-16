package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 13

object Day13 : AdventOfCode2019(DAY) {

    override fun part1() = arcadeCabinet.countBlock()

    override fun part2(): Any {
        return arcadeCabinet.bot()
    }

    private val arcadeCabinet = ArcadeCabinet(input().text())
}

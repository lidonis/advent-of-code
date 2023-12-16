package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 13

@Suppress("unused")
object Day13 : AdventOfCode2019(DAY) {

    private val arcadeCabinet = ArcadeCabinet(input().text())

    @Answer("452")
    override fun part1() = arcadeCabinet.countBlock()

    @Answer("21415")
    override fun part2() = arcadeCabinet.bot()
}

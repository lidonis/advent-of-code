package fr.lidonis.adventofcode.y2021.day16

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 16

@Suppress("unused")
object Day16 : AdventOfCode2021(DAY) {

    private val packetDecoder = PacketDecoder(input())

    @Answer("1358")
    override fun part1() = packetDecoder.part1()

    @Answer("1358")
    override fun part2() = packetDecoder.part2()
}

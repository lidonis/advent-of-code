package fr.lidonis.adventofcode.y2019.day22

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

object Day22 : AdventOfCode2019(22) {

    override fun part1() = SpaceCardMathShuffler(10007)
        .shuffle(instructions)
        .pow(-1)
        .compute(2019)

    override fun part2() = SpaceCardMathShuffler(119315717514047)
        .shuffle(instructions)
        .pow(101741582076661)
        .compute(2020)

    private val instructions = input().text()
}


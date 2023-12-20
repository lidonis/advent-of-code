package fr.lidonis.adventofcode.y2019.day22

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 22

@Suppress("unused")
object Day22 : AdventOfCode2019(DAY) {

    private const val PART_1_DECK_SIZE = 10007L
    private const val CARD_VALUE = 2019L
    private const val PART_2_DECK_SIZE = 119315717514047
    private const val CARD_INDEX = 2020L
    private const val NB_SHUFFLES = 101741582076661

    private val instructions = input().readText()

    @Answer("7171")
    override fun part1() = SpaceCardMathShuffler(PART_1_DECK_SIZE)
        .shuffle(instructions)
        .pow(-1)
        .compute(CARD_VALUE)

    @Answer("73394009116480")
    override fun part2() = SpaceCardMathShuffler(PART_2_DECK_SIZE)
        .shuffle(instructions)
        .pow(NB_SHUFFLES)
        .compute(CARD_INDEX)
}

package fr.lidonis.adventofcode.y2020.day25

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 25

private const val PART_2 = "Sorry for the trouble"

object Day25 : AdventOfCode2020(DAY) {

    @Answer("12181021")
    override fun part1() = encryptionKey()

    @Answer(PART_2)
    override fun part2() = PART_2

    private fun encryptionKey(): Long {
        val (cardPublicKey, doorPublicKey) = input().lines().map(kotlin.String::toLong)
        val cardLoopSize = ComboBreaker().findLoopSize(cardPublicKey)
        return ComboBreaker(doorPublicKey).computeEncryptionKey(cardLoopSize)
    }
}

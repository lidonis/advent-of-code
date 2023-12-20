package fr.lidonis.adventofcode.y2019.day16

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 16

@Suppress("unused")
object Day16 : AdventOfCode2019(DAY) {

    private const val TIMES = 100
    private const val RADIX = 10

    private val fft =
        FlawedFrequencyTransmission(
            input().map {
                Character.digit(it, RADIX)
            }
        )

    @Answer("84970726")
    override fun part1() = fft.phases(TIMES)

    @Answer("47664469")
    override fun part2() = fft.embedded(TIMES)
}

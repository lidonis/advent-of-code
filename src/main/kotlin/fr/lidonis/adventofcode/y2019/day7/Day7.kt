package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.common.permute
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 7

@Suppress("unused")
object Day7 : AdventOfCode2019(DAY) {

    private const val PART_1_RANGE_START = 0L
    private const val PART_1_RANGE_END = 4L
    private const val PART_2_RANGE_START = 5L
    private const val PART_2_RANGE_END = 9L

    private val program = input().text()

    @Answer("199988")
    override fun part1() = largestSignal(PART_1_RANGE_START..PART_1_RANGE_END) ?: error("No signal ...")

    @Answer("17519904")
    override fun part2() = largestSignal(PART_2_RANGE_START..PART_2_RANGE_END) ?: error("No signal ...")

    private fun largestSignal(range: LongRange) = range.toList().permute().map {
        Amplifiers(program, it).run()
    }.maxOrNull()
}

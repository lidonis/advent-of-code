package fr.lidonis.adventofcode.y2022.day7

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 7

private const val SIZE_PART1 = 100_000

@Suppress("unused")
object Day7 : AdventOfCode2022(DAY) {

    private val noSpaceLeftOnDevice = NoSpaceLeftOnDevice(input().readLines())

    @Answer("1770595")
    override fun part1() = noSpaceLeftOnDevice.smallerThan(SIZE_PART1).sum()

    @Answer("2195372")
    override fun part2() = noSpaceLeftOnDevice.sizeOfDirectoryToDelete()
}

package fr.lidonis.adventofcode.y2020.day17

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 17

@Suppress("unused")
object Day17 : AdventOfCode2020(DAY) {

    private const val BOOT_CYCLE_COUNT = 6

    private val conwayCubes = ConwayCubes(input().readLines())

    @Answer("310")
    override fun part1() = conwayCubes.cube(BOOT_CYCLE_COUNT)

    @Answer("2056")
    override fun part2() = conwayCubes.hypercube(BOOT_CYCLE_COUNT)
}

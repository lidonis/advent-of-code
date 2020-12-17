package fr.lidonis.adventofcode.y2020.day17

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 17

object Day17 : AdventOfCode2020(DAY) {

    private const val BOOT_CYCLE_COUNT = 6

    private val conwayCubes = ConwayCubes(input().lines())

    override fun part1(): Int {
        return conwayCubes.cube(BOOT_CYCLE_COUNT)
    }

    override fun part2() = conwayCubes.hypercube(BOOT_CYCLE_COUNT)
}

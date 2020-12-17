package fr.lidonis.adventofcode.y2019.day19

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 19

object Day19 : AdventOfCode2019(DAY) {

    private const val AREA_SIZE = 50
    private const val RECTANGLE_SIZE = 100

    private val tractorBeam = TractorBeam(input().text())

    override fun part1() = tractorBeam.countAffected(AREA_SIZE)

    override fun part2() = tractorBeam.fit(RECTANGLE_SIZE)
}

package fr.lidonis.adventofcode.y2019.day19

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 19

@Suppress("unused")
object Day19 : AdventOfCode2019(DAY) {

    private const val AREA_SIZE = 50
    private const val RECTANGLE_SIZE = 100

    private val tractorBeam = TractorBeam(input().text())

    @Answer("112")
    override fun part1() = tractorBeam.countAffected(AREA_SIZE)

    @Answer("18261982")
    override fun part2() = tractorBeam.fit(RECTANGLE_SIZE)
}

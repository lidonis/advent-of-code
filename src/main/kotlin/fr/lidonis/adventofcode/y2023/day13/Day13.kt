package fr.lidonis.adventofcode.y2023.day13

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 13

@Suppress("unused")
object Day13 : AdventOfCode2023(DAY) {

    private val pointOfIncidence = PointOfIncidence(input().text())

    @Answer("")
    override fun part1() = pointOfIncidence.part1()

    @Answer("")
    override fun part2() = pointOfIncidence.part2()
}

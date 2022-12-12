package fr.lidonis.adventofcode.y2022.day12

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 12

object Day12 : AdventOfCode2022(DAY) {

    private val hillClimbingAlgorithm = HillClimbingAlgorithm(input().lines())

    @Answer("490")
    override fun part1() = hillClimbingAlgorithm.fewestStepsFromStart() ?: error("no path")

    @Answer("488")
    override fun part2() = hillClimbingAlgorithm.fewestStepsFromA() ?: error("no path")
}

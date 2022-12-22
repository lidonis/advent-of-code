package fr.lidonis.adventofcode.y2022.day17

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 17

private const val NB_ROCKS_PART_1 = 2022L
private const val NB_ROCKS_PART_2 = 1000000000000

object Day17 : AdventOfCode2022(DAY) {

    private val pyroclasticFlow = PyroclasticFlow(input().text())

    @Answer("3119")
    override fun part1() = pyroclasticFlow.towerSize(NB_ROCKS_PART_1)

    @Answer("1536994219669")
    override fun part2() = pyroclasticFlow.towerSize(NB_ROCKS_PART_2)
}
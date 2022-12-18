package fr.lidonis.adventofcode.y2022.day8

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 8

@Suppress("unused")
object Day8: AdventOfCode2022(DAY) {

    private val treetopTreeHouse = TreetopTreeHouse(input().lines())

    @Answer("1717")
    override fun part1() = treetopTreeHouse.visibleTrees()

    @Answer("321975")
    override fun part2() = treetopTreeHouse.highestScenicScore()
}

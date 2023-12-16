package fr.lidonis.adventofcode.y2020.day22

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 22

@Suppress("unused")
object Day22 : AdventOfCode2020(DAY) {

    private val crabCombat = CrabCombat(input().text())

    @Answer("31314")
    override fun part1() = crabCombat.play()

    @Answer("32760")
    override fun part2() = crabCombat.playRecursive()
}
